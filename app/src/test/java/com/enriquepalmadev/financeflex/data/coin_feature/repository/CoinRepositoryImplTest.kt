package com.enriquepalmadev.financeflex.data.coin_feature.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.enriquepalmadev.financeflex.data.coin_feature.datasource.CoinRemoteDataSource
import com.enriquepalmadev.financeflex.data.coin_feature.dto.CoinDto
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.Either
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CoinRepositoryImplTest {

    private val getResponseCoinDto = listOf(
        CoinDto(
            id = "1",
            is_active = true,
            is_new = true,
            name = "Bitcoin",
            rank = 1,
            symbol = "BTC",
            type = ""
        ),
        CoinDto(
            id = "2",
            is_active = true,
            is_new = true,
            name = "Ethereum",
            rank = 2,
            symbol = "ETH",
            type = ""
        )
    )

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val coinRemoteDataSource = mockk<CoinRemoteDataSource>()
    private lateinit var coinRepositoryImpl: CoinRepositoryImpl

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher = StandardTestDispatcher())
        coinRepositoryImpl = CoinRepositoryImpl(coinRemoteDataSource)
    }

    @Test
    fun `WHEN fetchCoinList is successful, response is a list of comic list`() = runTest {

        // GIVEN
        coEvery { coinRemoteDataSource.fetchCoinsFromApi() } returns Either.Success(
            getResponseCoinDto
        )

        // WHEN
        val response = coinRepositoryImpl.fetchCoinList()

        // THEN
        assert(response is Either.Success<List<CoinModel>?>)
        coVerify(exactly = 1) { coinRemoteDataSource.fetchCoinsFromApi() }
    }
}