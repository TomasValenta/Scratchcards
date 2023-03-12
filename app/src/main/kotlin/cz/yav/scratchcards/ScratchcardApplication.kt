package cz.yav.scratchcards

import android.app.Application
import cz.yav.scratchcards.domain.manager.notification.NotificationManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ScratchcardApplication : Application() {

    @Inject
    lateinit var notificationManager: NotificationManager

}