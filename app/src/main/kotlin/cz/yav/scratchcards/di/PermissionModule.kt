package cz.yav.scratchcards.di

import cz.yav.scratchcards.domain.provider.PermissionProvider
import cz.yav.scratchcards.domain.provider.PermissionProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PermissionModule {

    @Binds
    abstract fun bindsPermissionProvider(
        permissionProviderImpl: PermissionProviderImpl
    ): PermissionProvider

}
