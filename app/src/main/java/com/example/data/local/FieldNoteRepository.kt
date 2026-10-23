package com.example.data.local

import com.example.data.local.entities.FieldNoteEntity
import kotlinx.coroutines.flow.Flow

class FieldNoteRepository(private val fieldNoteDao: FieldNoteDao) {

    val allNotes: Flow<List<FieldNoteEntity>> = fieldNoteDao.getAllNotes()

    fun getNotesByCrop(crop: String): Flow<List<FieldNoteEntity>> =
        fieldNoteDao.getNotesByCrop(crop)

    fun searchNotes(query: String): Flow<List<FieldNoteEntity>> =
        fieldNoteDao.searchNotes(query)

    suspend fun insertNote(note: FieldNoteEntity): Long =
        fieldNoteDao.insertNote(note)

    suspend fun deleteNote(note: FieldNoteEntity) =
        fieldNoteDao.deleteNote(note)

    suspend fun deleteNoteById(id: Long) =
        fieldNoteDao.deleteNoteById(id)
}
