package cz.yav.scratchcards.di

import cz.yav.scratchcards.domain.manager.notification.NotificationManager
import cz.yav.scratchcards.domain.manager.notification.NotificationManagerImpl
import cz.yav.scratchcards.domain.provider.NotificationProvider
import cz.yav.scratchcards.domain.provider.NotificationProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationModule {

    @Binds
    @Singleton
    abstract fun bindsNotificationManager(
        notificationManagerImpl: NotificationManagerImpl
    ): NotificationManager

    @Binds
    @Singleton
    abstract fun bindsNotificationProvider(
        notificationProviderImpl: NotificationProviderImpl
    ): NotificationProvider

}
