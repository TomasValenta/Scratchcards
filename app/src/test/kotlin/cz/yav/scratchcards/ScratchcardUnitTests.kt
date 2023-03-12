package cz.yav.scratchcards

import cz.yav.scratchcards.data.repository.model.ActivationResult
import cz.yav.scratchcards.data.repository.model.Scratchcard
import cz.yav.scratchcards.domain.usecase.ActivateScratchcardUseCase
import cz.yav.scratchcards.domain.usecase.GetScratchcardUseCase
import cz.yav.scratchcards.domain.usecase.GetScratchcardsUseCase
import cz.yav.scratchcards.domain.usecase.UpdateScratchcardUseCase
import cz.yav.scratchcards.mock.ScratchcardsRepositoryMockImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class ScratchcardUnitTests {

    @Test
    fun getScratchcardUseCaseTest() {
        val testedUUID = UUID.randomUUID().toString()
        val testedScratchcard = Scratchcard(value = 55, uuid = testedUUID)
        val useCase = GetScratchcardUseCase(ScratchcardsRepositoryMockImpl(setOf(testedScratchcard)))

        val receivedScratchcard = runBlocking { useCase.invoke(testedUUID) } ?: throw AssertionError()

        assertEquals(receivedScratchcard.uuid, testedScratchcard.uuid)
        assertEquals(receivedScratchcard.value, testedScratchcard.value)
    }

    @Test
    fun getScratchcardsUseCaseTest() {
        val testedScratchcards = setOf(Scratchcard(value = 55), Scratchcard(value = 12))
        val useCase = GetScratchcardsUseCase(ScratchcardsRepositoryMockImpl(testedScratchcards))

        val receivedScratchcard = runBlocking { useCase.invoke().first() }

        assertEquals(receivedScratchcard, testedScratchcards)
    }

    @Test
    fun updateScratchcardUseCaseTest() {
        val testedScratchcard = Scratchcard(value = 55)
        val testedScratchcardModified = testedScratchcard.copy(state = Scratchcard.State.Scratched(code = UUID.randomUUID().toString()))
        val testedScratchcards = setOf(testedScratchcard, Scratchcard(value = 12))
        val scratchcardsRepositoryMock = ScratchcardsRepositoryMockImpl(testedScratchcards)
        val updateScratchcardUseCase = UpdateScratchcardUseCase(scratchcardsRepositoryMock)
        val getScratchcardUseCase = GetScratchcardUseCase(scratchcardsRepositoryMock)

        runBlocking { updateScratchcardUseCase.invoke(testedScratchcardModified) }
        val receivedScratchcard = runBlocking { getScratchcardUseCase.invoke(testedScratchcard.uuid) }

        assertEquals(receivedScratchcard, testedScratchcardModified)
    }

    @Test
    fun activateScratchcardUseCaseNotScratchedTest() {
        val testedScratchcard = Scratchcard(value = 55)
        val scratchcardsRepositoryMock = ScratchcardsRepositoryMockImpl(setOf(testedScratchcard))
        val activateScratchcardUseCase = ActivateScratchcardUseCase(scratchcardsRepositoryMock, UpdateScratchcardUseCase(scratchcardsRepositoryMock))
        val getScratchcardUseCase = GetScratchcardUseCase(scratchcardsRepositoryMock)

        runBlocking { activateScratchcardUseCase.invoke(testedScratchcard) }
        val receivedScratchcard = runBlocking { getScratchcardUseCase.invoke(testedScratchcard.uuid) }

        assertEquals(receivedScratchcard, testedScratchcard)
    }

    @Test
    fun activateScratchcardUseCaseFailureTest() {
        val testedScratchcard = Scratchcard(value = 55, state = Scratchcard.State.Scratched(code = UUID.randomUUID().toString()))
        val scratchcardsRepositoryMock = ScratchcardsRepositoryMockImpl(setOf(testedScratchcard))
        val activateScratchcardUseCase = ActivateScratchcardUseCase(scratchcardsRepositoryMock, UpdateScratchcardUseCase(scratchcardsRepositoryMock))
        val getScratchcardUseCase = GetScratchcardUseCase(scratchcardsRepositoryMock)

        runBlocking { activateScratchcardUseCase.invoke(testedScratchcard) }
        val receivedScratchcard = runBlocking { getScratchcardUseCase.invoke(testedScratchcard.uuid) }

        assertEquals(receivedScratchcard, testedScratchcard)
    }

    @Test
    fun activateScratchcardUseCaseSuccessRejectedTest() {
        val testedScratchcard = Scratchcard(value = 55, state = Scratchcard.State.Scratched(code = UUID.randomUUID().toString()))
        val scratchcardsRepositoryMock = ScratchcardsRepositoryMockImpl(setOf(testedScratchcard), ActivationResult.Success(value = -1))
        val activateScratchcardUseCase = ActivateScratchcardUseCase(scratchcardsRepositoryMock, UpdateScratchcardUseCase(scratchcardsRepositoryMock))
        val getScratchcardUseCase = GetScratchcardUseCase(scratchcardsRepositoryMock)

        runBlocking { activateScratchcardUseCase.invoke(testedScratchcard) }
        val receivedScratchcard = runBlocking { getScratchcardUseCase.invoke(testedScratchcard.uuid) } ?: throw AssertionError()

        assertEquals(receivedScratchcard.state, Scratchcard.State.Rejected(code = testedScratchcard.state.code ?: ""))
    }

    @Test
    fun activateScratchcardUseCaseSuccessActivatedTest() {
        val testedScratchcard = Scratchcard(value = 55, state = Scratchcard.State.Scratched(code = UUID.randomUUID().toString()))
        val scratchcardsRepositoryMock = ScratchcardsRepositoryMockImpl(setOf(testedScratchcard), ActivationResult.Success(value = 80_001))
        val activateScratchcardUseCase = ActivateScratchcardUseCase(scratchcardsRepositoryMock, UpdateScratchcardUseCase(scratchcardsRepositoryMock))
        val getScratchcardUseCase = GetScratchcardUseCase(scratchcardsRepositoryMock)

        runBlocking { activateScratchcardUseCase.invoke(testedScratchcard) }
        val receivedScratchcard = runBlocking { getScratchcardUseCase.invoke(testedScratchcard.uuid) } ?: throw AssertionError()

        assertEquals(receivedScratchcard.state, Scratchcard.State.Activated(code = testedScratchcard.state.code ?: ""))
    }
}