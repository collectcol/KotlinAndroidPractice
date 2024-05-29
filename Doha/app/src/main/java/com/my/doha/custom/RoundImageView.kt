package com.my.doha.custom

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
    }
    private val path = Path()

    init {
        if (attrs == null) {
            scaleType = ScaleType.CENTER_CROP
        }
    }

    override fun onDraw(canvas: Canvas?) {
        val width = width - paddingLeft - paddingRight
        val height = height - paddingTop - paddingBottom
        val radius = Math.min(width, height) / 6f

        path.reset()
        path.addRoundRect(
            RectF(paddingLeft.toFloat(), paddingTop.toFloat(), (width + paddingLeft).toFloat(), (height + paddingTop).toFloat()),
            radius, radius,
            Path.Direction.CW
        )
        canvas?.clipPath(path)
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        val radius = Math.min(w, h) / 6f
        path.addRoundRect(
            RectF(0f, 0f, w.toFloat(), h.toFloat()),
            radius, radius,
            Path.Direction.CW
        )
    }
}