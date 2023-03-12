package cz.yav.scratchcards.di

import cz.yav.scratchcards.data.source.remote.client.unauthorizedHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Unauthorized
    fun providesUnauthorizedHttpClient() = unauthorizedHttpClient
}
