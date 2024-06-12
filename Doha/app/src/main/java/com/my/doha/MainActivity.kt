package com.my.doha

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.my.doha.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView
    private lateinit var mToolbar: Toolbar
    private lateinit var mBottomNavigation: BottomNavigationView
    private lateinit var mCalendarContainer: View
    private lateinit var mCalendarCustom: RecyclerView
    private lateinit var mFragmentContainer: View
    private lateinit var mDohaAppContext: Context
    private lateinit var mDohaAppInstance: DohaApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDohaAppContext = DohaApp.applicationContext()
        mDohaAppInstance = mDohaAppContext as DohaApp

        mDrawerLayout = findViewById(R.id.drawer_layout)
        mNavigationView = findViewById(R.id.navigation_view)
        mToolbar = findViewById(R.id.toolbar)
        mCalendarContainer = findViewById(R.id.calendar_container)
        mCalendarCustom = findViewById<RecyclerView?>(R.id.calendar_custom).apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = RecyclerViewAdapterMonth(this@MainActivity, mDohaAppInstance.mCalendar)
            scrollToPosition(Int.MAX_VALUE / 2)
        }
        mFragmentContainer = findViewById(R.id.fragment_container)

        setSupportActionBar(mToolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            mDrawerLayout,
            mToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.menu_icon)
        }

        mNavigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handle home click
                }

                R.id.nav_profile -> {
                    // Handle profile click
                }

                R.id.nav_settings -> {
                    // Handle settings click
                }
            }
            mDrawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        mBottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_navigation_schedule -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, ScheduleFragment()).commit()
//                        supportActionBar!!.title = getString(R.string.bottomNavigation_first)
                        true
                    }

                    R.id.bottom_navigation_account_book -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, AccountBookFragment()).commit()
//                        supportActionBar!!.title = getString(R.string.bottomNavigation_second)
                        true
                    }

                    R.id.bottom_navigation_note -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, NoteFragment()).commit()
//                        supportActionBar!!.title = getString(R.string.bottomNavigation_third)
                        true
                    }

                    R.id.bottom_navigation_practice -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, PracticeFragment()).commit()
//                        supportActionBar!!.title = getString(R.string.bottomNavigation_force)
                        true
                    }

                    R.id.bottom_navigation_diary -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, DiaryFragment()).commit()
//                        supportActionBar!!.title = getString(R.string.bottomNavigation_fifth)
                        true
                    }

                    else -> {
                        false
                    }
                }
            }
            selectedItemId = R.id.bottom_navigation_schedule
        }

//        mFragmentContainer.setOnTouchListener(ResizeTouchListener())
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

//    private inner class ResizeTouchListener : View.OnTouchListener {
//        private var initialY = 0f
//        private var initialWeightFragment = 0f
//        private var initialWeightCalendar = 0f
//
//        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//            when (event?.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    initialY = event.rawY
//                    initialWeightFragment =
//                        (mFragmentContainer.layoutParams as LinearLayout.LayoutParams).weight
//                    initialWeightCalendar =
//                        (mCalendarContainer.layoutParams as LinearLayout.LayoutParams).weight
//                }
//
//                MotionEvent.ACTION_MOVE -> {
//                    val deltaY = initialY - event.rawY
//                    val totalHeight = mCalendarContainer.height + mFragmentContainer.height
//                    val fragmentWeight = initialWeightFragment + deltaY / totalHeight
//                    val calendarWeight = initialWeightCalendar - deltaY / totalHeight
//
//                    val easedFragmentWeight = fragmentWeight.coerceIn(0.1f, 0.9f)
//                    val easedCalendarWeight = calendarWeight.coerceIn(0.1f, 0.9f)
//
//                    if (easedFragmentWeight > 0 && easedCalendarWeight > 0) {
//                        (mFragmentContainer.layoutParams as LinearLayout.LayoutParams).weight = easedFragmentWeight
//                        (mCalendarContainer.layoutParams as LinearLayout.LayoutParams).weight = easedCalendarWeight
//
//                        mFragmentContainer.requestLayout()
//                        mCalendarContainer.requestLayout()
//                    }
//                }
//            }
//            return true
//        }
//    }
}
