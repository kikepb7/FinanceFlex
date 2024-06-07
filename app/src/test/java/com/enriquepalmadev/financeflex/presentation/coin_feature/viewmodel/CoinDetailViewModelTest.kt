package com.enriquepalmadev.financeflex.presentation.coin_feature.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.enriquepalmadev.financeflex.R
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.Either
import com.enriquepalmadev.financeflex.domain.coin_feature.model.FailureDomain
import com.enriquepalmadev.financeflex.domain.coin_feature.usecase.FetchCoinListUseCase
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListScreenEmpty
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListScreenLoading
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListScreenModel
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListScreenState
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.ScreenError
import com.enriquepalmadev.financeflex.presentation.utils.toCoinListModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CoinDetailViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val fetchCoinListUseCase = mockk<FetchCoinListUseCase>()
    private lateinit var viewModel: CoinListViewModel
    private val coinListCoinModelMocked = listOf(
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

    private val errorMocked = ScreenError(
        image = R.drawable.error_logo,
        errorMsg = R.string.connection_error
    )
    private val exceptionMocked = ScreenError(
        image = R.drawable.error_logo,
        errorMsg = R.string.exception_error
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher = StandardTestDispatcher())
        viewModel = CoinListViewModel(fetchCoinListUseCase = fetchCoinListUseCase)
    }

    @Test
    fun `GIVEN fetch coin list WHEN we call fetchCoinListUseCase method and there's an service error THENit return a failure object`() {
        return runTest {

            coEvery { fetchCoinListUseCase.fetchCoinList() } returns flow {
                emit(
                    Either.Failure(
                        FailureDomain.ApiError
                    )
                )
            }

            viewModel.state.test {
                // First State
                assertEquals(
                    CoinListScreenState(
                        loadingScreenData = CoinListScreenLoading(loader = false)
                    ), awaitItem()
                )

                viewModel.getCoinList()

                // Updated State

                assertEquals(
                    CoinListScreenState(
                        loadingScreenData = CoinListScreenLoading(loader = true)
                    ), awaitItem()
                )

                assertEquals(
                    CoinListScreenState(
                        loadingScreenData = CoinListScreenLoading(loader = false),
                        errorScreenData = errorMocked
                    ), awaitItem()
                )

                // It finish to emit
                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `GIVEN fetch coin list WHEN we call fetchCoinListUseCase method and there's an exception THEN it return a throwable error`() {
        return runTest {

            coEvery { fetchCoinListUseCase.fetchCoinList() } returns flow { throw Exception() }

            viewModel.state.test {
                // First State
                assertEquals(
                    CoinListScreenState(
                        loadingScreenData = CoinListScreenLoading(loader = false)
                    ), awaitItem()
                )

                viewModel.getCoinList()

                // Updated State
                assertEquals(
                    CoinListScreenState(
                        loadingScreenData = CoinListScreenLoading(loader = true)
                    ), awaitItem()
                )

                assertEquals(
                    CoinListScreenState(
                        loadingScreenData = CoinListScreenLoading(loader = false),
                        errorScreenData = exceptionMocked
                    ), awaitItem()
                )

                // It finish to emit
                cancelAndConsumeRemainingEvents()
            }
        }
    }


    @Test
    fun `GIVEN fetch coin list WHEN we call fetchCoinListUseCase method and the response is success THEN it return a coin list`() {
        return runTest {
            coEvery { fetchCoinListUseCase.fetchCoinList() } returns flow {
                emit(
                    Either.Success(
                        data = coinListCoinModelMocked    // Si pasamos un emptyList() debe dar error
                    )
                )
            }

            viewModel.state.test {
                // First State
                assertEquals(
                    CoinListScreenState(
                        loadingScreenData = CoinListScreenLoading(loader = false)
                    ), awaitItem()
                )

                viewModel.getCoinList()

                // Updated state
                assertEquals(
                    CoinListScreenState(
                        loadingScreenData = CoinListScreenLoading(loader = true)
                    ), awaitItem()
                )

                assertEquals(
                    CoinListScreenState(
                        loadingScreenData = CoinListScreenLoading(loader = false),
                        coinScreenData = CoinListScreenModel(
                            coinListModel = coinListCoinModelMocked.toCoinListModel()
                        )
                    ), awaitItem()
                )

                // It finish to emit
                cancelAndConsumeRemainingEvents()
            }
        }
    }
}