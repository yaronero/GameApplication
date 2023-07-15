package com.example.gameapplication.domain.repositories

import com.example.gameapplication.domain.model.ApplicationMode

interface MainRepository {

    suspend fun checkMode(): ApplicationMode
}