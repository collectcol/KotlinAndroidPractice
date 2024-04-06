package com.example.fastcampus_wookyung

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class Intent_Two : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_two)

        // Intent_One -> Intent_Two
        // Intent_Two 액티비티를 호출한 Activity의 intent이다
        val intent = intent // Intent_One의 Intent
        val data : String? = intent.extras?.getString("extra-data")
        if(data != null){
            Log.d("dataa", data)
        }

        (findViewById<TextView>(R.id.finish)).apply {
            this.setOnClickListener {
                val intent: Intent = Intent()
                intent.putExtra("result", "success")
                setResult(RESULT_OK, intent)
                // Intent_One -> Intent_Two 를 종료시킨다
                finish()
            }
        }

        // intent에서 이미지 받기
        val imageView = findViewById<ImageView>(R.id.imageView)
        // Intent_One에서 받은 이미지를 Uri 형태로 다시 받기
        var uri = Uri.parse(intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
        imageView.setImageURI(uri)
    }
}