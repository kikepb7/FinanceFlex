package com.enriquepalmadev.financeflex.data.portfolio_feature.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InvestDao {
    @Insert
    fun addInvest(invest: InvestEntity)

    @Query("SELECT * FROM invests")
    fun getInvest(): Flow<List<InvestEntity>>

    @Update
    fun updateInvest(todo: InvestEntity)

    @Delete
    fun deleteInvest(todo: InvestEntity)
}