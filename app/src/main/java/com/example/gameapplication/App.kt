package com.example.gameapplication

import android.app.Application
import com.example.gameapplication.di.dataModule
import com.example.gameapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, viewModelModule))
        }
    }
}