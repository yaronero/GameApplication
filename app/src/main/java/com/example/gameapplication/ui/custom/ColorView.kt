package com.example.gameapplication.ui.custom

import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.toColor
import com.example.gameapplication.utils.toPx

class ColorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private var fillPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.GREEN
    }

    private var backgroundPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.BLACK
    }

    private val backgroundRect = RectF(0F, 0F, SIZE_DP.toPx, SIZE_DP.toPx)
    private val rect =
        RectF(STROKE_WIDTH, STROKE_WIDTH, SIZE_DP.toPx - STROKE_WIDTH, SIZE_DP.toPx - STROKE_WIDTH)

    init {
        if (resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
        ) {
            backgroundPaint.color = Color.WHITE
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = SIZE_DP.toPx + paddingLeft + paddingRight
        val desiredHeight = SIZE_DP.toPx + paddingTop + paddingBottom

        setMeasuredDimension(
            measureDimension(desiredWidth.toInt(), widthMeasureSpec),
            measureDimension(desiredHeight.toInt(), heightMeasureSpec)
        )
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        return when (specMode) {
            MeasureSpec.EXACTLY -> specSize
            MeasureSpec.AT_MOST -> desiredSize.coerceAtMost(specSize)
            else -> desiredSize
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRoundRect(
            backgroundRect,
            BORDER_RADIUS_DP.toPx,
            BORDER_RADIUS_DP.toPx,
            backgroundPaint
        )
        canvas?.drawRoundRect(rect, BORDER_RADIUS_DP.toPx, BORDER_RADIUS_DP.toPx, fillPaint)
    }

    fun setColor(color: Color) {
        fillPaint.color = color.toArgb()
    }

    fun getColor() = fillPaint.color.toColor()

    companion object {
        private const val SIZE_DP = 50f
        private const val BORDER_RADIUS_DP = 10f

        private const val STROKE_WIDTH = 5f
    }
}