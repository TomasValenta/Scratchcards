package cz.yav.scratchcards.domain.provider

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import cz.yav.scratchcards.extension.isAtLeastTiramisu
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PermissionProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : PermissionProvider {

    override fun isPermissionGranted(permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    override val isPostNotificationsPermissionGranted: Boolean
        get() = if (isAtLeastTiramisu) isPermissionGranted(Manifest.permission.POST_NOTIFICATIONS) else true

}
