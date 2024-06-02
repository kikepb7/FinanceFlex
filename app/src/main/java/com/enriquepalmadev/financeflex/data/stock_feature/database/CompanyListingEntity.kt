package com.enriquepalmadev.financeflex.data.stock_feature.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyListingEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val symbol: String,
    val exchange: String,
    val assetType: String,
    val ipoDate: String,
    val delistingDate: String,
    val status: String
)
