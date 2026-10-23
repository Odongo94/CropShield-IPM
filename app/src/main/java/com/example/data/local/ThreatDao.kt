package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.local.entities.ChemicalSolutionEntity
import com.example.data.local.entities.IpmNonChemicalEntity
import com.example.data.local.entities.LifecycleStageEntity
import com.example.data.local.entities.ThreatEntity
import com.example.data.local.entities.ThreatWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface ThreatDao {

    @Transaction
    @Query("SELECT * FROM threats ORDER BY name ASC")
    fun getAllThreatsWithDetails(): Flow<List<ThreatWithDetails>>

    @Transaction
    @Query("SELECT * FROM threats WHERE id = :id")
    fun getThreatWithDetailsById(id: String): Flow<ThreatWithDetails?>

    @Transaction
    @Query("SELECT * FROM threats WHERE type = :type ORDER BY name ASC")
    fun getThreatsByType(type: String): Flow<List<ThreatWithDetails>>

    @Transaction
    @Query("""
        SELECT * FROM threats 
        WHERE name LIKE '%' || :query || '%' 
           OR scientificName LIKE '%' || :query || '%' 
           OR targetCropsJson LIKE '%' || :query || '%'
           OR summary LIKE '%' || :query || '%'
        ORDER BY name ASC
    """)
    fun searchThreatsWithDetails(query: String): Flow<List<ThreatWithDetails>>

    @Query("SELECT COUNT(*) FROM threats")
    suspend fun getThreatCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThreat(threat: ThreatEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThreats(threats: List<ThreatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLifecycleStages(stages: List<LifecycleStageEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChemicals(chemicals: List<ChemicalSolutionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNonChemicals(nonChemicals: List<IpmNonChemicalEntity>)

    @Transaction
    suspend fun insertFullThreat(
        threat: ThreatEntity,
        stages: List<LifecycleStageEntity>,
        chemicals: List<ChemicalSolutionEntity>,
        nonChemicals: List<IpmNonChemicalEntity>
    ) {
        insertThreat(threat)
        insertLifecycleStages(stages)
        insertChemicals(chemicals)
        insertNonChemicals(nonChemicals)
    }
}
