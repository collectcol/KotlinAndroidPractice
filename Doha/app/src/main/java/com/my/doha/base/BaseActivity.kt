package com.my.doha.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.my.doha.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        supportActionBar?.apply {
//            setDisplayUseLogoEnabled(true)
//            setDisplayShowTitleEnabled(true)
//            setDisplayShowHomeEnabled(true)
//            setIcon(null)
//            elevation = 0f
//        }
    }
}