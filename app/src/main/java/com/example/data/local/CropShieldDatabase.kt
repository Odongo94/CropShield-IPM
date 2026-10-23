package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.ThreatCatalog
import com.example.data.local.entities.ChemicalSolutionEntity
import com.example.data.local.entities.FieldNoteEntity
import com.example.data.local.entities.IpmNonChemicalEntity
import com.example.data.local.entities.LifecycleStageEntity
import com.example.data.local.entities.ThreatEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        ScoutRecordEntity::class,
        ThreatEntity::class,
        LifecycleStageEntity::class,
        ChemicalSolutionEntity::class,
        IpmNonChemicalEntity::class,
        FieldNoteEntity::class,
        com.example.data.local.entities.MappedFieldEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class CropShieldDatabase : RoomDatabase() {

    abstract fun scoutDao(): ScoutDao
    abstract fun threatDao(): ThreatDao
    abstract fun fieldNoteDao(): FieldNoteDao
    abstract fun mappedFieldDao(): MappedFieldDao

    companion object {
        @Volatile
        private var INSTANCE: CropShieldDatabase? = null

        fun getDatabase(context: Context): CropShieldDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CropShieldDatabase::class.java,
                    "cropshield_ipm_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Pre-populate Room database in background on first creation
                            prepopulateDatabase(context.applicationContext)
                        }

                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            // Also ensure tables are populated on open if empty
                            prepopulateDatabase(context.applicationContext)
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private fun prepopulateDatabase(context: Context) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val db = getDatabase(context)
                    if (db.threatDao().getThreatCount() == 0) {
                        for (threat in ThreatCatalog.allThreats) {
                            val bundle = ThreatDatabaseMapper.toEntities(threat)
                            db.threatDao().insertFullThreat(
                                threat = bundle.threat,
                                stages = bundle.stages,
                                chemicals = bundle.chemicals,
                                nonChemicals = bundle.nonChemicals
                            )
                        }
                    }

                    if (db.fieldNoteDao().getNoteCount() == 0) {
                        db.fieldNoteDao().insertNote(
                            FieldNoteEntity(
                                cropName = "Tomato",
                                pestOrDisease = "Late Blight",
                                noteText = "Noticed first water-soaked spot on bottom leaf margin after 2 days of rain. Pruned lower branches.",
                                locationTag = "Block 2 - West Ridge",
                                dateCreated = "Oct 14, 2026 • 08:30 AM"
                            )
                        )
                        db.fieldNoteDao().insertNote(
                            FieldNoteEntity(
                                cropName = "Maize",
                                pestOrDisease = "Fall Armyworm",
                                noteText = "Found windowing damage on 3 whorls. Larvae are in 1st instar. Applied neem extract spray.",
                                locationTag = "North Valley Field",
                                dateCreated = "Oct 15, 2026 • 07:15 AM"
                            )
                        )
                    }

                    if (db.mappedFieldDao().getFieldCount() == 0) {
                        db.mappedFieldDao().insertMappedField(
                            com.example.data.local.entities.MappedFieldEntity(
                                name = "Njoro Central Shamba - Plot 1",
                                cropName = "Maize",
                                hectares = 1.25,
                                acres = 3.09,
                                perimeterMeters = 458.0,
                                pointsJson = "-0.3031,36.0800;-0.3021,36.0815;-0.3035,36.0822;-0.3045,36.0805",
                                zoneName = "Rift Valley Cereal & Dairy Belt",
                                soilType = "Mollic Andosols (Volcanic Ash Loam)",
                                slopeCategory = "Gentle slope (3.8%) • Standard agronomic contour practice",
                                notes = "Planted H6213 hybrid maize. Soil tests show mild phosphorus deficiency. Basal DAP applied at 50kg/ha.",
                                dateCreated = "Oct 16, 2026 • 09:45 AM"
                            )
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
