package com.enriquepalmadev.financeflex.domain.model

import com.enriquepalmadev.financeflex.data.dto.TeamMemberDto

data class CoinModel(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)

data class CoinDetailModel(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMemberDto>
)