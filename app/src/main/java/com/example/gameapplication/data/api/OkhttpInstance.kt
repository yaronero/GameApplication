package com.example.gameapplication.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class OkhttpInstance {

    fun getInstance(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    companion object {
        const val BASE_URL = "https://yearnewsantasa.live"
    }
}