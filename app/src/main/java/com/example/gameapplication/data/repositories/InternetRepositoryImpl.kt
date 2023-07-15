package com.example.gameapplication.data.repositories

import com.example.gameapplication.data.api.OkhttpInstance
import com.example.gameapplication.domain.model.ApplicationMode
import com.example.gameapplication.domain.repositories.InternetRepository
import okhttp3.OkHttpClient
import okhttp3.Request

class InternetRepositoryImpl(
    private val okHttpClient: OkHttpClient
) : InternetRepository {

    override suspend fun checkConnection(): ApplicationMode {
        val request = Request.Builder()
            .url("${OkhttpInstance.BASE_URL}/test.txt")
            .build()

        return try {
            val execution = okHttpClient.newCall(request).execute()
            val body = execution.body?.string().toBoolean()
            if (body) {
                ApplicationMode.Online
            } else {
                ApplicationMode.Offline
            }
        } catch (e: Exception) {
            ApplicationMode.Offline
        }
    }
}