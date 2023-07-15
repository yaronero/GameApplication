package com.example.gameapplication.ui.game

import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.gameapplication.domain.repositories.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(
    private val gameRepository: GameRepository
) : ViewModel() {

    private val _colors = MutableStateFlow<List<Color>>(listOf())
    val colors: StateFlow<List<Color>> = _colors.asStateFlow()

    private val _colorsToGuess = MutableStateFlow<List<Color>>(listOf())
    val colorsToGuess: StateFlow<List<Color>> = _colorsToGuess.asStateFlow()

    init {
        getColors()
    }

    private fun getColors() {
        _colors.value = gameRepository.getColors()
        _colorsToGuess.value = gameRepository.getColorsToGuess()
    }
}