package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "field_notes")
data class FieldNoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cropName: String,
    val pestOrDisease: String,
    val noteText: String,
    val locationTag: String,
    val dateCreated: String,
    val timestamp: Long = System.currentTimeMillis()
)
