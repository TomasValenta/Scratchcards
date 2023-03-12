package cz.yav.scratchcards.di

import cz.yav.scratchcards.data.source.remote.ActivationRemoteDataSource
import cz.yav.scratchcards.data.source.remote.ActivationRemoteDataSourceImpl
import cz.yav.scratchcards.data.repository.ScratchcardsRepository
import cz.yav.scratchcards.data.repository.ScratchcardsRepositoryImpl
import cz.yav.scratchcards.domain.usecase.ActivateScratchcardUseCase
import cz.yav.scratchcards.domain.usecase.GetScratchcardUseCase
import cz.yav.scratchcards.domain.usecase.GetScratchcardsUseCase
import cz.yav.scratchcards.domain.usecase.UpdateScratchcardUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ScratchcardsModule {

    @Binds
    @Singleton
    abstract fun bindsScratchcardsRepository(
        scratchcardsRepositoryImpl: ScratchcardsRepositoryImpl,
    ): ScratchcardsRepository

    @Binds
    abstract fun bindsActivationRemoteDataSource(
        activationRemoteDataSourceImpl: ActivationRemoteDataSourceImpl,
    ): ActivationRemoteDataSource

    companion object {

        @Provides
        fun provideGetScratchcardUseCase(
            scratchcardsRepository: ScratchcardsRepository,
        ) = GetScratchcardUseCase(scratchcardsRepository)

        @Provides
        fun provideGetScratchcardsUseCase(
            scratchcardsRepository: ScratchcardsRepository,
        ) = GetScratchcardsUseCase(scratchcardsRepository)

        @Provides
        fun provideUpdateScratchcardUseCase(
            scratchcardsRepository: ScratchcardsRepository,
        ) = UpdateScratchcardUseCase(scratchcardsRepository)

        @Provides
        @Singleton
        fun provideActivateScratchcardUseCase(
            scratchcardsRepository: ScratchcardsRepository,
            updateScratchcardUseCase: UpdateScratchcardUseCase,
        ) = ActivateScratchcardUseCase(scratchcardsRepository, updateScratchcardUseCase)

    }
}
