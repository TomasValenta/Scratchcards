package cz.yav.scratchcards.presentation.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.yav.scratchcards.data.repository.model.Scratchcard
import cz.yav.scratchcards.domain.manager.navigation.NavigationManager
import cz.yav.scratchcards.domain.manager.navigation.model.Command
import cz.yav.scratchcards.domain.usecase.GetScratchcardsUseCase
import cz.yav.scratchcards.presentation.common.mapper.toScratchcardUiState
import cz.yav.scratchcards.presentation.navigation.ScratchcardRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewScreenViewModel @Inject constructor(
    getScratchcardsUseCase: GetScratchcardsUseCase,
    private val navigationManager: NavigationManager,
) : ViewModel() {

    private val scratchcards = MutableStateFlow(emptySet<Scratchcard>())

    val state = scratchcards
        .map { cards ->
            if (cards.isEmpty()) {
                OverviewScreenUiState.Loading
            } else {
                OverviewScreenUiState.Success(
                    cardsUiState = cards.map { it.toOverviewScratchcardUiState() }
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), OverviewScreenUiState.Loading)

    init {
        getScratchcardsUseCase()
            .onEach {
                this.scratchcards.value = it
            }
            .launchIn(viewModelScope)
    }

    fun onScratchClick(cardUiState: OverviewScreenUiState.CardUiState) = viewModelScope.launch {
        navigationManager.navigate(
            Command.Destination("${ScratchcardRoute.Scratch.routeRoot}/${cardUiState.uuid}"),
        )
    }

    fun onActivateClick(cardUiState: OverviewScreenUiState.CardUiState) = viewModelScope.launch {
        navigationManager.navigate(
            Command.Destination("${ScratchcardRoute.Activation.routeRoot}/${cardUiState.uuid}"),
        )
    }
}

private fun Scratchcard.toOverviewScratchcardUiState() = OverviewScreenUiState.CardUiState(
    uuid = uuid,
    state = toScratchcardUiState(),
)
