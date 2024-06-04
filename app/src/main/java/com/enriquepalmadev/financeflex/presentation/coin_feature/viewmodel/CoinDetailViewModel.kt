package com.enriquepalmadev.financeflex.presentation.coin_feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enriquepalmadev.financeflex.domain.coin_feature.model.CoinDetailModel
import com.enriquepalmadev.financeflex.domain.coin_feature.model.Either
import com.enriquepalmadev.financeflex.domain.coin_feature.model.FailureDomain
import com.enriquepalmadev.financeflex.domain.coin_feature.usecase.FetchCoinDetailUseCase
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinDetailScreenModel
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinDetailScreenState
import com.enriquepalmadev.financeflex.presentation.coin_feature.model.CoinListScreenLoading
import com.enriquepalmadev.financeflex.presentation.utils.toComicListScreenError
import com.enriquepalmadev.financeflex.presentation.utils.toEmptyListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val fetchCoinDetailUseCase: FetchCoinDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoinDetailScreenState())
    val state: StateFlow<CoinDetailScreenState?> = _state.asStateFlow()

    private val _event = MutableStateFlow<Event?>(null)
    val event: StateFlow<Event?> = _event.asStateFlow()

    fun getCoinDetail(coinId: String) {
        viewModelScope.launch {
            fetchCoinDetailUseCase.fetchCoinDetail(coinId = coinId)
                .onStart { manageLoading() }
                .catch { throwableError -> manageError(throwableError = throwableError) }
                .collect { result ->
                    when (result) {
                        is Either.Failure -> {
                            manageFailure(error = result.error)
                        }

                        is Either.Success -> {
                            manageSuccess(result.data)
                        }
                    }
                }
        }
    }

    private fun manageLoading() {
        _state.update { coinScreenState ->
            coinScreenState.copy(
                loadingScreenData = CoinListScreenLoading(loader = true)
            )
        }
    }

    private fun manageFailure(error: FailureDomain) {
        _state.update { coinScreenState ->
            coinScreenState.copy(
                errorScreenData = error.toComicListScreenError(),
                loadingScreenData = CoinListScreenLoading(loader = false)
            )
        }
    }

    private fun manageError(throwableError: Throwable) {
        _state.update { coinScreenState ->
            coinScreenState.copy(
                errorScreenData = throwableError.toComicListScreenError(),
                loadingScreenData = CoinListScreenLoading(loader = false)
            )
        }
    }

    private fun manageSuccess(data: CoinDetailModel?) {
        if (data != null) {
            _state.update { coinScreenState ->
                coinScreenState.copy(
                    coinDetailScreenData = CoinDetailScreenModel(
                        coinDetail = data
                    ),
                    loadingScreenData = CoinListScreenLoading(loader = false)
                )
            }
        } else {
            manageEmptyList()
        }
    }

    private fun manageEmptyList() {
        _state.update { coinScreenState ->
            coinScreenState.copy(
                emptyListScreenData = toEmptyListModel(),
                loadingScreenData = CoinListScreenLoading(loader = false)
            )
        }
    }
}