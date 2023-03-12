package cz.yav.scratchcards.extension

import androidx.lifecycle.SavedStateHandle

/** Gets navigation argument or throw IllegalArgumentException */
fun <T> SavedStateHandle.getOrThrow(key: String): T =
    get(key) ?: throw IllegalArgumentException("Required value for the key: $key is not available!")
