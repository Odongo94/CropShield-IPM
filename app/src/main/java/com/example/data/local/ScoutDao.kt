package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoutDao {
    @Query("SELECT * FROM scout_records ORDER BY timestamp DESC")
    fun getAllRecords(): Flow<List<ScoutRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: ScoutRecordEntity): Long

    @Delete
    suspend fun deleteRecord(record: ScoutRecordEntity)

    @Query("DELETE FROM scout_records WHERE id = :id")
    suspend fun deleteRecordById(id: Long)

    @Query("SELECT COUNT(*) FROM scout_records")
    fun getRecordCount(): Flow<Int>
}
