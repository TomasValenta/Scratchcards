package cz.yav.scratchcards.di

import javax.inject.Qualifier

/* Qualifies unauthorized HTTP client dependency. */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Unauthorized
