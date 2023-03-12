package cz.yav.scratchcards.domain.provider

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import cz.yav.scratchcards.R
import cz.yav.scratchcards.extension.isAtLeastOreo
import cz.yav.scratchcards.presentation.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : NotificationProvider {

    private val manager: NotificationManagerCompat = NotificationManagerCompat.from(context)
    private val channelId: String = createNotificationChannel(context, manager)

    override val areNotificationsEnabled: Boolean
        get() = manager.areNotificationsEnabled()

    override fun showNotification(id: Int, titleText: String, contentText: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_scratchcard)
            .setContentTitle(titleText)
            .setContentText(contentText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE))
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) { notify(id, builder.build()) }
    }
}

private fun createNotificationChannel(context: Context, manager: NotificationManagerCompat): String {
    if (isAtLeastOreo) {
        val channel = NotificationChannel(
            "ScratchcardsChannel",
            context.getString(R.string.scratchcards_channel),
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = context.getString(R.string.scratchcard_activation_notifications_text)
        }
        manager.createNotificationChannel(channel)
        return channel.id
    }
    return ""
}
