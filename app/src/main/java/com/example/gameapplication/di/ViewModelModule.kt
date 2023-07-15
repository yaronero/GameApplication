package com.example.gameapplication.di

import com.example.gameapplication.ui.game.GameViewModel
import com.example.gameapplication.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SplashViewModel(get())
    }
    viewModel {
        GameViewModel(get())
    }
}