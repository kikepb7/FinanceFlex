package com.enriquepalmadev.financeflex.data.portfolio_feature.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [InvestEntity::class], version = 1)
abstract class InvestDatabase : RoomDatabase () {
    abstract fun investDao(): InvestDao
}