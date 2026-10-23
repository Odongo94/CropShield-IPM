package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entities.FieldNoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FieldNoteDao {

    @Query("SELECT * FROM field_notes ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<FieldNoteEntity>>

    @Query("SELECT * FROM field_notes WHERE cropName = :crop ORDER BY timestamp DESC")
    fun getNotesByCrop(crop: String): Flow<List<FieldNoteEntity>>

    @Query("SELECT * FROM field_notes WHERE noteText LIKE '%' || :query || '%' OR pestOrDisease LIKE '%' || :query || '%' OR cropName LIKE '%' || :query || '%' ORDER BY timestamp DESC")
    fun searchNotes(query: String): Flow<List<FieldNoteEntity>>

    @Query("SELECT COUNT(*) FROM field_notes")
    suspend fun getNoteCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: FieldNoteEntity): Long

    @Delete
    suspend fun deleteNote(note: FieldNoteEntity)

    @Query("DELETE FROM field_notes WHERE id = :id")
    suspend fun deleteNoteById(id: Long)
}
