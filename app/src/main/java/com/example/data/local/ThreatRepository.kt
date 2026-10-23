package com.example.data.local

import com.example.data.ThreatCatalog
import com.example.model.CropThreat
import com.example.model.ThreatType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThreatRepository(private val threatDao: ThreatDao) {

    val allThreats: Flow<List<CropThreat>> = threatDao.getAllThreatsWithDetails()
        .map { list ->
            if (list.isEmpty()) {
                ThreatCatalog.allThreats
            } else {
                list.map { ThreatDatabaseMapper.toDomainModel(it) }
            }
        }

    fun searchThreats(query: String): Flow<List<CropThreat>> {
        val trimmed = query.trim()
        return if (trimmed.isBlank()) {
            allThreats
        } else {
            threatDao.searchThreatsWithDetails(trimmed).map { list ->
                val roomResults = list.map { ThreatDatabaseMapper.toDomainModel(it) }
                val catalogResults = ThreatCatalog.filterThreats(query = trimmed)
                (roomResults + catalogResults).distinctBy { it.id }
            }
        }
    }

    fun getThreatById(id: String): Flow<CropThreat?> {
        return threatDao.getThreatWithDetailsById(id).map { details ->
            if (details != null) {
                ThreatDatabaseMapper.toDomainModel(details)
            } else {
                ThreatCatalog.getThreatById(id)
            }
        }
    }

    fun getThreatsByType(type: ThreatType): Flow<List<CropThreat>> {
        return threatDao.getThreatsByType(type.name).map { list ->
            if (list.isEmpty()) {
                ThreatCatalog.allThreats.filter { it.type == type }
            } else {
                list.map { ThreatDatabaseMapper.toDomainModel(it) }
            }
        }
    }

    suspend fun insertOrUpdateThreat(threat: CropThreat) {
        val bundle = ThreatDatabaseMapper.toEntities(threat)
        threatDao.insertFullThreat(
            threat = bundle.threat,
            stages = bundle.stages,
            chemicals = bundle.chemicals,
            nonChemicals = bundle.nonChemicals
        )
    }

    suspend fun ensureSeeded() {
        for (threat in ThreatCatalog.allThreats) {
            insertOrUpdateThreat(threat)
        }
    }
}
