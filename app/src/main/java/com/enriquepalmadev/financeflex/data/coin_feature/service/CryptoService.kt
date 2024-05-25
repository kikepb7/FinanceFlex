package com.enriquepalmadev.financeflex.data.coin_feature.service

import com.enriquepalmadev.financeflex.data.coin_feature.dto.CoinDetailDto
import com.enriquepalmadev.financeflex.data.coin_feature.dto.CoinDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoService {
    @GET("v1/coins")
    fun getCoins(): Call<List<CoinDto>>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): Response<CoinDetailDto>
}