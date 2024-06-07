package com.enriquepalmadev.financeflex.data.portfolio_feature.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date

@Entity(tableName = "invests")
data class InvestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "invest")
    val invest: String,
    @ColumnInfo(name = "avgPrice")
    val avgPrice: String,
    @ColumnInfo(name = "amount")
    val amount: String,
    @ColumnInfo(name = "done")
    val done: Boolean = false,
    @ColumnInfo(name = "added")
    val added: Long = System.currentTimeMillis()
)

val InvestEntity.addDate: String get() = SimpleDateFormat("yyyy/MM/dd hh:mm").format(Date(added))