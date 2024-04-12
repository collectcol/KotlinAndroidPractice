package com.example.fastcampus_wookyung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        val imageView = findViewById<ImageView>(R.id.image)

        Glide
            .with(this)
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT0sncWCzz9t3udH4HZwqeMQ0nmoSLTQV3ZxOvjIk-m0w&s")
            .centerCrop() // 여백없이 꽉 채우기
            .into(imageView)
    }
}