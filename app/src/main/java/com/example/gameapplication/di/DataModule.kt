package com.example.gameapplication.di

import com.example.gameapplication.data.api.OkhttpInstance
import com.example.gameapplication.data.repositories.DatastoreRepositoryImpl
import com.example.gameapplication.data.repositories.GameRepositoryImpl
import com.example.gameapplication.data.repositories.InternetRepositoryImpl
import com.example.gameapplication.data.repositories.ModeRepositoryImpl
import com.example.gameapplication.domain.repositories.DatastoreRepository
import com.example.gameapplication.domain.repositories.GameRepository
import com.example.gameapplication.domain.repositories.InternetRepository
import com.example.gameapplication.domain.repositories.MainRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        OkhttpInstance().getInstance()
    }

    single<DatastoreRepository> {
        DatastoreRepositoryImpl(get())
    }

    single<InternetRepository> {
        InternetRepositoryImpl(get())
    }

    single<MainRepository> {
        ModeRepositoryImpl(get(), get())
    }

    single<GameRepository> {
        GameRepositoryImpl()
    }
}