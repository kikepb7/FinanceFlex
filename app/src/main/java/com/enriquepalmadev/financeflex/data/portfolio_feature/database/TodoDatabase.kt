package com.enriquepalmadev.financeflex.data.portfolio_feature.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase () {
    abstract fun todoDao(): TodoDao
}