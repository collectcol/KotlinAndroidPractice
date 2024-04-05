package com.example.fastcampus_wookyung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var viewcontrol02Button: Button
    private lateinit var calculatorButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent : Intent
        viewcontrol02Button = findViewById(R.id.viewControl02)
        viewcontrol02Button.setOnClickListener {
            intent = Intent(this, ViewControl_02::class.java)
            startActivity(intent)
        }

        calculatorButton = findViewById(R.id.calculatorbtn)
        calculatorButton.setOnClickListener {
            intent = Intent(this, Calculator::class.java)
            startActivity(intent)
        }
    }

}

