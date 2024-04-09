package com.example.fastcampus_wookyung

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var viewcontrol02Button: Button
    private lateinit var calculatorButton: Button
    private lateinit var intentButton: Button

    private val INTERNET_PERMISSION_REQUEST_CODE = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent: Intent
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

        intentButton = findViewById(R.id.intent)
        intentButton.setOnClickListener {
            intent = Intent(this, Intent_One::class.java)
            startActivity(intent)
        }

        (findViewById<Button>(R.id.stack)).setOnClickListener {
            startActivity(Intent(this@MainActivity, ActivityOne::class.java))
        }

        (findViewById<Button>(R.id.webview)).setOnClickListener {
            startActivity(Intent(this@MainActivity, WebViewLoad::class.java))
        }

        (findViewById<Button>(R.id.fragmentActivity)).setOnClickListener {
            startActivity(Intent(this@MainActivity, FragmentActivity::class.java))
        }

        (findViewById<Button>(R.id.asyncActivity)).setOnClickListener {
            startActivity(Intent(this@MainActivity, AsyncActivity::class.java))
        }
        (findViewById<Button>(R.id.applicationContext)).setOnClickListener {
            startActivity(Intent(this@MainActivity, ApplicationActivityOne::class.java))
        }

    }
}

