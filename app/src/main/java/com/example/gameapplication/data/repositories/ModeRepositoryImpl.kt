package com.example.gameapplication.data.repositories

import com.example.gameapplication.domain.model.ApplicationMode
import com.example.gameapplication.domain.repositories.DatastoreRepository
import com.example.gameapplication.domain.repositories.InternetRepository
import com.example.gameapplication.domain.repositories.MainRepository
import kotlinx.coroutines.flow.first

class ModeRepositoryImpl(
    private val datastoreRepository: DatastoreRepository,
    private val internetRepository: InternetRepository,
) : MainRepository {

    override suspend fun checkMode(): ApplicationMode {
        var appMode = datastoreRepository.getStringApplicationModel.first()
        if (appMode == ApplicationMode.Undefined) {
            appMode = internetRepository.checkConnection()
            datastoreRepository.saveApplicationMode(appMode)
        }
        return appMode
    }
}