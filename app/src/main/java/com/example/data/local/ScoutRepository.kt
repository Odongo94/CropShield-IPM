package com.example.data.local

import kotlinx.coroutines.flow.Flow

class ScoutRepository(private val scoutDao: ScoutDao) {

    val allRecords: Flow<List<ScoutRecordEntity>> = scoutDao.getAllRecords()
    val recordCount: Flow<Int> = scoutDao.getRecordCount()

    suspend fun insert(record: ScoutRecordEntity): Long = scoutDao.insertRecord(record)

    suspend fun delete(record: ScoutRecordEntity) = scoutDao.deleteRecord(record)

    suspend fun deleteById(id: Long) = scoutDao.deleteRecordById(id)
}
