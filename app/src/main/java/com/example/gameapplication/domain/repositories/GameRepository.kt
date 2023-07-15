package com.example.gameapplication.domain.repositories

import android.graphics.Color

interface GameRepository {

    fun getColors(): List<Color>
    fun getColorsToGuess(): List<Color>
}