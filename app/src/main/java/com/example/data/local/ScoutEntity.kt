package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scout_records")
data class ScoutRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dateString: String,
    val fieldOrPlot: String,
    val cropName: String,
    val threatName: String,
    val threatType: String, // PEST or DISEASE
    val severityLevel: String, // Mild, Moderate, Severe, Critical
    val affectedPlantCount: String,
    val actionTaken: String,
    val notes: String,
    val timestamp: Long = System.currentTimeMillis()
)
