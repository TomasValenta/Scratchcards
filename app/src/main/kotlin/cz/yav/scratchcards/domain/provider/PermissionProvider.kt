package cz.yav.scratchcards.domain.provider

interface PermissionProvider {
    fun isPermissionGranted(permission: String): Boolean
    val isPostNotificationsPermissionGranted: Boolean
}