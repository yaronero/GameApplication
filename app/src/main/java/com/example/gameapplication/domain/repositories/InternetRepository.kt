package com.example.gameapplication.domain.repositories

import com.example.gameapplication.domain.model.ApplicationMode

interface InternetRepository {

    suspend fun checkConnection(): ApplicationMode
}