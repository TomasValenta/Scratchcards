package cz.yav.scratchcards.domain.provider

interface NotificationProvider {
    val areNotificationsEnabled: Boolean
    fun showNotification(id: Int, titleText: String, contentText: String)
}