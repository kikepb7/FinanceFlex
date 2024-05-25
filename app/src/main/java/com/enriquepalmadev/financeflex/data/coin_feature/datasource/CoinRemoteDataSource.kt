package com.enriquepalmadev.financeflex.data.coin_feature.datasource

import com.enriquepalmadev.financeflex.data.coin_feature.dto.CoinDetailDto
import com.enriquepalmadev.financeflex.data.coin_feature.dto.CoinDto
import com.enriquepalmadev.financeflex.data.coin_feature.dto.FailureDto
import com.enriquepalmadev.financeflex.data.coin_feature.service.CryptoService
import com.enriquepalmadev.financeflex.domain.coin_feature.model.Either
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CoinRemoteDataSource @Inject constructor(
    private val retrofitService: CryptoService
) {
    suspend fun fetchCoinsFromApi(): Either<FailureDto, List<CoinDto>?> {
        val request = retrofitService.getCoins().await()

        return try {
            if (request.isSuccessful && request.body() != null) {
                Either.Success(data = request.body())
            } else {
                Either.Failure(
                    error = FailureDto(
                        code = request.code(),
                        message = request.errorBody().toString()
                    )
                )
            }
        } catch (e: Exception) {
            Either.Failure(
                FailureDto(
                    code = request.code(),
                    message = request.errorBody().toString()
                )
            )
        }
    }

    suspend fun fetchCoinDetailFromApi(coinId: String): Either<FailureDto, CoinDetailDto?> {
        val request = retrofitService.getCoinById(coinId = coinId)

        return try {
            if (request.isSuccessful && request.body() != null) {
                Either.Success(data = request.body())
            } else {
                Either.Failure(
                    error = FailureDto(
                        code = request.code(),
                        message = request.errorBody().toString()
                    )
                )
            }
        } catch (e: Exception) {
            Either.Failure(
                FailureDto(
                    code = request.code(),
                    message = request.errorBody().toString()
                )
            )
        }
    }

    private suspend fun <T> Call<T>.await(): Response<T> {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    continuation.resume(response)
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}