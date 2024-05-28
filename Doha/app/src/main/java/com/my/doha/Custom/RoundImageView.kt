package com.my.doha.Custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class RoundImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {
    private val paint = Paint().apply {
        isAntiAlias = true
    }   // https://iamnaran.medium.com/create-a-custom-imageview-android-bd5602f70a5c
    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
        val width = width.toFloat()
        val height = height.toFloat()
        val radius = Math.min(width, height) / 2

        path.reset()
        path.addRoundRect(
            RectF(0f, 0f, width, height),
            radius, radius,
            Path.Direction.CW
        )
        canvas?.clipPath(path)
        super.onDraw(canvas)
    }
}