package com.my.doha

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.tabs.TabLayout.LabelVisibility
import com.my.doha.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var mToolbar: Toolbar
    private lateinit var mBottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar!!.apply {
            setDisplayShowTitleEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.menu_icon)
        }


        mBottomNavigation = findViewById<BottomNavigationView?>(R.id.bottom_navigation).apply {

            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_navigation_schedule -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, ScheduleFragment()).commit()
                        supportActionBar!!.title = getString(R.string.bottomNavigation_first)
                        true
                    }
                    R.id.bottom_navigation_account_book -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, AccountBookFragment()).commit()
                        supportActionBar!!.title = getString(R.string.bottomNavigation_second)
                        true
                    }
                    R.id.bottom_navigation_note -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, NoteFragment()).commit()
                        supportActionBar!!.title = getString(R.string.bottomNavigation_third)
                        true
                    }
                    R.id.bottom_navigation_practice -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, PracticeFragment()).commit()
                        supportActionBar!!.title = getString(R.string.bottomNavigation_force)
                        true
                    }
                    R.id.bottom_navigation_diary -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, DiaryFragment()).commit()
                        supportActionBar!!.title = getString(R.string.bottomNavigation_fifth)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
            selectedItemId = R.id.bottom_navigation_schedule
        }
    }
}