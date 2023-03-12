package cz.yav.scratchcards.presentation.activation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.yav.scratchcards.data.repository.model.Scratchcard
import cz.yav.scratchcards.domain.provider.PermissionProvider
import cz.yav.scratchcards.domain.usecase.ActivateScratchcardUseCase
import cz.yav.scratchcards.domain.usecase.GetScratchcardUseCase
import cz.yav.scratchcards.domain.usecase.GetScratchcardsUseCase
import cz.yav.scratchcards.extension.getOrThrow
import cz.yav.scratchcards.presentation.common.mapper.toScratchcardUiState
import cz.yav.scratchcards.presentation.navigation.ScratchcardRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivationScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getScratchcardUseCase: GetScratchcardUseCase,
    getScratchcardsUseCase: GetScratchcardsUseCase,
    private val permissionProvider: PermissionProvider,
    private val activateScratchcardUseCase: ActivateScratchcardUseCase,
) : ViewModel() {

    private val scratchCardUUID = savedStateHandle.getOrThrow<String>(ScratchcardRoute.Activation.argumentName)

    private val scratchcard = MutableStateFlow<Scratchcard?>(null)
    private val postNotificationsPermissionVisible = MutableStateFlow(false)

    val state = combine(
        scratchcard,
        postNotificationsPermissionVisible
    ) { scratchcard, postNotificationsPermissionVisible ->
        when (scratchcard) {
            null -> ActivationScreenUiState.Loading
            else -> ActivationScreenUiState.Success(
                cardState = scratchcard.toScratchcardUiState(scratchButtonVisible = false),
                postNotificationsPermissionVisible = postNotificationsPermissionVisible
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ActivationScreenUiState.Loading)

    init {
        viewModelScope.launch {

            launch {
                val obtainedScratchcard = getScratchcardUseCase(scratchCardUUID) ?: throw IllegalArgumentException("Provided scratchCardUUID: $scratchCardUUID is not valid!")
                if (obtainedScratchcard.state !is Scratchcard.State.Scratched) throw IllegalStateException("Provided Scratchcard: $scratchCardUUID is not scratched!")

                scratchcard.value = obtainedScratchcard
            }

            launch {
                getScratchcardsUseCase()
                    .mapNotNull { cards -> cards.firstOrNull { it.uuid == scratchCardUUID } }
                    .collect {
                        scratchcard.value = it
                        if (it.state is Scratchcard.State.ActivationInProgress && !permissionProvider.isPostNotificationsPermissionGranted) {
                            postNotificationsPermissionVisible.value = true
                        }
                    }
            }
        }
    }

    fun onActivateClick() {
        scratchcard.value?.let { notActivatedCard ->
            CoroutineScope(Dispatchers.IO).launch {
                activateScratchcardUseCase(notActivatedCard)
            }
        }
    }

    fun onPostNotificationsConfirmButtonClick() {
        postNotificationsPermissionVisible.value = false
    }

    fun onPostNotificationsDismissButtonClick() {
        postNotificationsPermissionVisible.value = false
    }
}
