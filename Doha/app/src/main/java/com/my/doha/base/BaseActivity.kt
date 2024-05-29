package com.my.doha.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.my.doha.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var bar = supportActionBar
        if (bar != null) {
            bar.setDisplayUseLogoEnabled(true)
            bar.setDisplayShowTitleEnabled(true)
            bar.setDisplayShowHomeEnabled(true)
            bar.setIcon(R.drawable.main_logo);
        }
    }
}