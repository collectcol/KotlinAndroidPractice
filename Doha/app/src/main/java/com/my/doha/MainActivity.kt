package com.my.doha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.my.doha.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}