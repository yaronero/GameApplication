package com.example.gameapplication.utils

import android.graphics.Color
import androidx.core.graphics.toColor

fun List<Color>.getMixedColor(): Color {
    val redList = mutableListOf<Int>()
    val greenList = mutableListOf<Int>()
    val blueList = mutableListOf<Int>()

    this.forEach {
        val argb = it.toArgb()
        redList.add(Color.red(argb))
        greenList.add(Color.green(argb))
        blueList.add(Color.blue(argb))
    }

    val mixedRed = redList.average().toInt()
    val mixedGreen = greenList.average().toInt()
    val mixedBlue = blueList.average().toInt()

    return Color.rgb(mixedRed, mixedGreen, mixedBlue).toColor()
}