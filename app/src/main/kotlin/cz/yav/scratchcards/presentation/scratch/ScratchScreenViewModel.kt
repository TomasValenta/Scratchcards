package cz.yav.scratchcards.presentation.scratch

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.yav.scratchcards.data.repository.model.Scratchcard
import cz.yav.scratchcards.domain.usecase.GetScratchcardUseCase
import cz.yav.scratchcards.domain.usecase.UpdateScratchcardUseCase
import cz.yav.scratchcards.extension.getOrThrow
import cz.yav.scratchcards.presentation.common.mapper.toScratchcardUiState
import cz.yav.scratchcards.presentation.navigation.ScratchcardRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ScratchScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getScratchcardUseCase: GetScratchcardUseCase,
    private val updateScratchcardUseCase: UpdateScratchcardUseCase,
) : ViewModel() {

    private val scratchCardUUID = savedStateHandle.getOrThrow<String>(ScratchcardRoute.Scratch.argumentName)

    private val scratchcard = MutableStateFlow<Scratchcard?>(null)

    val state = scratchcard
        .map {
            when (it) {
                null -> ScratchScreenUiState.Loading
                else -> ScratchScreenUiState.Success(cardState = it.toScratchcardUiState(activateButtonVisible = false))
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ScratchScreenUiState.Loading)

    init {
        viewModelScope.launch {
            scratchcard.value = getScratchcardUseCase(scratchCardUUID)
                ?: throw IllegalArgumentException("Provided scratchCardUUID: $scratchCardUUID is not valid!")
        }
    }

    fun onScratchClick() {
        scratchcard.value?.let { notScratchedCard ->
            viewModelScope.launch {
                scratchcard.value = notScratchedCard.copy(state = Scratchcard.State.ScratchInProgress)

                delay(2000)

                val scratchedCard = notScratchedCard.copy(state = Scratchcard.State.Scratched(code = UUID.randomUUID().toString()))
                updateScratchcardUseCase(scratchedCard)
                scratchcard.value = scratchedCard
            }
        }
    }
}
