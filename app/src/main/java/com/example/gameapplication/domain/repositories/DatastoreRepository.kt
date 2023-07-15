package com.example.gameapplication.domain.repositories

import com.example.gameapplication.domain.model.ApplicationMode
import kotlinx.coroutines.flow.Flow

interface DatastoreRepository {

    val getStringApplicationModel: Flow<ApplicationMode>

    suspend fun saveApplicationMode(mode: ApplicationMode)
}