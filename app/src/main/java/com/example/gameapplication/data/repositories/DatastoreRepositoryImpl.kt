package com.example.gameapplication.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gameapplication.domain.model.ApplicationMode
import com.example.gameapplication.domain.model.getApplicationModeFromString
import com.example.gameapplication.domain.model.getStringFromApplicationMode
import com.example.gameapplication.domain.repositories.DatastoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreRepositoryImpl(
    private val context: Context
) : DatastoreRepository {

    override val getStringApplicationModel: Flow<ApplicationMode> =
        context.dataStore.data.map { preferences ->
            val mode = preferences[APPLICATION_MODE_KEY]
            mode.getApplicationModeFromString()
        }

    override suspend fun saveApplicationMode(mode: ApplicationMode) {
        context.dataStore.edit { preferences ->
            preferences[APPLICATION_MODE_KEY] = mode.getStringFromApplicationMode()
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("applicationMode")

        private val APPLICATION_MODE_KEY = stringPreferencesKey("applicationMode")
    }
}