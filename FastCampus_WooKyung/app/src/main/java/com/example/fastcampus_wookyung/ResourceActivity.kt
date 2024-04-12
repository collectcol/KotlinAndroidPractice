package com.example.fastcampus_wookyung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class ResourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        findViewById<TextView>(R.id.text).setOnClickListener {
            (it as TextView).text = resources.getText(R.string.app_name)

            // 리소스에 접근하는 방법
            it.background = resources.getDrawable(R.drawable.dog_image, null) // null을 넣어줘도 되지만 this.theme 를 넣어도 된다 (테마)
            it.background = ContextCompat.getDrawable(this, R.drawable.dog_image)
            it.background = ResourcesCompat.getDrawable(resources, R.drawable.dog_image, null)

        }
        findViewById<ImageView>(R.id.image).setOnClickListener {
            (it as ImageView).setImageDrawable(
                resources.getDrawable(R.drawable.round_button2, this.theme)
            )
        }
    }
}