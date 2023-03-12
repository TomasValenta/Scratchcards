package cz.yav.scratchcards.extension

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

private val buildSdkInt = Build.VERSION.SDK_INT

val isOreo: Boolean = buildSdkInt == Build.VERSION_CODES.O

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
val isAtLeastOreo: Boolean = buildSdkInt >= Build.VERSION_CODES.O

val isS: Boolean = buildSdkInt == Build.VERSION_CODES.S

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
val isAtLeastS: Boolean = buildSdkInt >= Build.VERSION_CODES.S

val isTiramisu: Boolean = buildSdkInt == Build.VERSION_CODES.TIRAMISU

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
val isAtLeastTiramisu: Boolean = buildSdkInt >= Build.VERSION_CODES.TIRAMISU
