package com.enriquepalmadev.financeflex.domain.coin_feature.usecase

import com.enriquepalmadev.financeflex.domain.coin_feature.CoinRepository
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.Either
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchCoinDetailUseCaseTest {

    private val coinRepository = mockk<CoinRepository>()
    private lateinit var fethCoinListUseCase: FetchCoinListUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher = StandardTestDispatcher())
        fethCoinListUseCase = FetchCoinListUseCase(coinRepository)
    }

    @Test
    fun `Check if Repository is called`() = runTest {
        // Given
        coEvery { coinRepository.fetchCoinList() } returns Either.Success(getCoinListMocked())

        // When
        val result = fethCoinListUseCase.fetchCoinList().first()

        // Then
        coVerify(exactly = 1) { coinRepository.fetchCoinList() }
        assertEquals(result, Either.Success(getCoinListMocked()))
    }

    private fun getCoinListMocked() = listOf(
        CoinModel(
            id = "1",
            isActive = true,
            name = "Bitcoin",
            rank = 1,
            symbol = "BTC"
        ),
        CoinModel(
            id = "2",
            isActive = true,
            name = "Ethereum",
            rank = 2,
            symbol = "ETH"
        )
    )
}