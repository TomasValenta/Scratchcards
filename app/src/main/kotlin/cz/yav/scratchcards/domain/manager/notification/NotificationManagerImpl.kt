package cz.yav.scratchcards.domain.manager.notification

import android.content.Context
import cz.yav.scratchcards.R
import cz.yav.scratchcards.data.repository.model.Scratchcard
import cz.yav.scratchcards.domain.provider.NotificationProvider
import cz.yav.scratchcards.domain.usecase.GetScratchcardsUseCase
import cz.yav.scratchcards.domain.usecase.UpdateScratchcardUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationManagerImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val notificationProvider: NotificationProvider,
    private val getScratchcardsUseCase: GetScratchcardsUseCase,
    private val updateScratchcardUseCase: UpdateScratchcardUseCase,
) : NotificationManager {

    init {
        CoroutineScope(Dispatchers.Default)
            .launch {
                getScratchcardsUseCase()
                    .map { cards -> cards.filter { it.state is Scratchcard.State.Rejected && !it.state.notified } }
                    .collect { cards ->
                        if (notificationProvider.areNotificationsEnabled) {
                            cards.forEach {
                                notificationProvider.showNotification(
                                    id = it.uuid.hashCode(),
                                    titleText = context.getString(R.string.scratchcard_value, it.value),
                                    contentText = context.getString(R.string.scratchcard_rejected_try_another_one)
                                )
                                updateScratchcardUseCase(it.copy(state = Scratchcard.State.Rejected(code = (it.state as Scratchcard.State.Rejected).code, notified = true)))
                            }
                        }
                    }
            }
    }
}
