package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entities.MappedFieldEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MappedFieldDao {

    @Query("SELECT * FROM mapped_fields ORDER BY id DESC")
    fun getAllMappedFields(): Flow<List<MappedFieldEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMappedField(field: MappedFieldEntity): Long

    @Delete
    suspend fun deleteMappedField(field: MappedFieldEntity)

    @Query("SELECT * FROM mapped_fields WHERE id = :fieldId LIMIT 1")
    suspend fun getFieldById(fieldId: Long): MappedFieldEntity?

    @Query("SELECT COUNT(*) FROM mapped_fields")
    suspend fun getFieldCount(): Int
}
