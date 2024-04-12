package com.example.fastcampus_wookyung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ApplicationActivityOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_one)

        findViewById<TextView>(R.id.changeActivity).setOnClickListener {
            startActivity(Intent(this, ApplicationActivityTwo::class.java))
        }

        findViewById<TextView>(R.id.testMethod).setOnClickListener {
            startActivity(Intent(this, ApplicationActivityTwo::class.java))

            // Application Context에 접근해서 함수 호출하기
            (applicationContext as MasterApplication).methodFromApplication()
            (applicationContext as MasterApplication).userId
        }
    }
}