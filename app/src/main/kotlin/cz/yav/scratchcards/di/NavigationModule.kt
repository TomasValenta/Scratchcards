package cz.yav.scratchcards.di

import cz.yav.scratchcards.domain.manager.navigation.NavigationManager
import cz.yav.scratchcards.domain.manager.navigation.NavigationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun bindsNavigationManager(
        navigationManagerImpl: NavigationManagerImpl
    ): NavigationManager

}
