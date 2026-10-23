package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mapped_fields")
data class MappedFieldEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val cropName: String,
    val hectares: Double,
    val acres: Double,
    val perimeterMeters: Double,
    val pointsJson: String, // Comma-separated or JSON list of lat:lng coordinates
    val zoneName: String,
    val soilType: String,
    val slopeCategory: String,
    val notes: String = "",
    val dateCreated: String
)
