package com.my.doha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.tabs.TabLayout.LabelVisibility
import com.my.doha.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var mBottomNavigation : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavigation = findViewById(R.id.bottom_navigation)

    }
}