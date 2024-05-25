package com.enriquepalmadev.financeflex.data.coin_feature.dto

import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    val description: String,
    @SerializedName("development_status")
    val developmentStatus: String,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String,
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("last_data_at")
    val lastDataAt: String,
    val links: LinksDto,
    @SerializedName("links_extended")
    val linksExtended: List<LinksExtendedDto>,
    val message: String,
    val name: String,
    @SerializedName("open_source")
    val openSource: Boolean,
    @SerializedName("org_structure")
    val orgStructure: String,
    @SerializedName("proof_type")
    val proofType: String,
    val rank: Int,
    @SerializedName("started_at")
    val startedAt: String,
    val symbol: String,
    val tags: List<TagDto>,
    val team: List<TeamMemberDto>,
    val type: String,
    val whitepaper: WhitepaperDto
)

data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

data class LinksDto(
    val explorer: List<String>,
    val facebook: List<String>,
    val reddit: List<String>,
    val sourceCode: List<String>,
    val website: List<String>,
    val youtube: List<String>
)

data class LinksExtendedDto(
    val stats: StatsDto,
    val type: String,
    val url: String
)

data class StatsDto(
    val contributors: Int,
    val followers: Int,
    val stars: Int,
    val subscribers: Int
)

data class TagDto(
    val coinCounter: Int,
    val icoCounter: Int,
    val id: String,
    val name: String
)

data class TeamMemberDto(
    val id: String,
    val name: String,
    val position: String
)

data class WhitepaperDto(
    val link: String,
    val thumbnail: String
)