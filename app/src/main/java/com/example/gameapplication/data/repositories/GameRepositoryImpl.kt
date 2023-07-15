package com.example.gameapplication.data.repositories

import android.graphics.Color
import androidx.core.graphics.toColor
import com.example.gameapplication.domain.repositories.GameRepository
import com.example.gameapplication.utils.getMixedColor
import kotlin.random.Random

class GameRepositoryImpl : GameRepository {

    private val colors = mutableListOf<Color>()
    private val colorsToGuess = mutableListOf<Color>()

    override fun getColors(): List<Color> {
        colors.apply {
            clear()
            addAll((0..Random.nextInt(1, 6)).map {
                Color.rgb(
                    Random.nextInt(MAX_COLOR_INT),
                    Random.nextInt(MAX_COLOR_INT),
                    Random.nextInt(MAX_COLOR_INT)
                ).toColor()
            })
        }

        return colors
    }

    override fun getColorsToGuess(): List<Color> {
        colorsToGuess.apply {
            clear()
            addAll((0..4).map {
                Color.rgb(
                    Random.nextInt(MAX_COLOR_INT),
                    Random.nextInt(MAX_COLOR_INT),
                    Random.nextInt(MAX_COLOR_INT)
                ).toColor()
            })
            val color = colors.getMixedColor()
            add(color)
            shuffle()
        }
        return colorsToGuess
    }

    companion object {
        private const val MAX_COLOR_INT = 256
    }
}