package com.my.doha

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
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.my.doha.base.BaseActivity
import java.time.DayOfWeek
import java.util.Calendar

class MainActivity : BaseActivity(){
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView
    private lateinit var mToolbar: Toolbar
    private lateinit var mBottomNavigation: BottomNavigationView
    private lateinit var mCalendarContainer: View
    private lateinit var mFragmentContainer: View

//    private lateinit var mTextViewMonth: TextView

    // 요일 리스트
    private lateinit var mWeekList: MutableList<String>
    private lateinit var mDayList: MutableList<String>

    // 그리드뷰
    private lateinit var mRecyclerViewDayList: RecyclerView
    private lateinit var mRecyclerViewWeekList: RecyclerView

    private lateinit var mCalendar: Calendar

    private lateinit var mDohaAppContext: DohaApp

    companion object {
        var YEAR: String = "년"
        var MONTH: String = "월"
        var DAY: String = "일"

        var TAG: String = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initWeekList()
        initDayList()

        mDohaAppContext = applicationContext as DohaApp

        mDrawerLayout = findViewById(R.id.drawer_layout)
        mNavigationView = findViewById(R.id.navigation_view)
        mToolbar = findViewById(R.id.toolbar)
        mCalendarContainer = findViewById(R.id.calendar_container)
        mRecyclerViewWeekList = findViewById<RecyclerView?>(R.id.recyclerView_weeklist).apply {
            this.layoutManager = GridLayoutManager(this@MainActivity, 7)
            this.setItemViewCacheSize(7)
            this.adapter = RecyclerViewWeekListAdapter(mWeekList, LayoutInflater.from(this@MainActivity))
        }
        mRecyclerViewDayList = findViewById<RecyclerView?>(R.id.recyclerView_daylist).apply {
            this.layoutManager = GridLayoutManager(this@MainActivity, 7)
            this.setItemViewCacheSize(45)
            this.adapter = RecyclerViewDayListAdapter(mDayList, LayoutInflater.from(this@MainActivity))
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

        mFragmentContainer.setOnTouchListener(ResizeTouchListener())
    }

    private fun initWeekList(){
        mWeekList = mutableListOf()
        mWeekList.add("일")
        mWeekList.add("월")
        mWeekList.add("화")
        mWeekList.add("수")
        mWeekList.add("목")
        mWeekList.add("금")
        mWeekList.add("토")
    }

    private fun initDayList() {
        mDayList = mutableListOf()
        mDayList.add("테스트")
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private inner class ResizeTouchListener : View.OnTouchListener {
        private var initialY = 0f
        private var initialWeightFragment = 0f
        private var initialWeightCalendar = 0f

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialY = event.rawY
                    initialWeightFragment =
                        (mFragmentContainer.layoutParams as LinearLayout.LayoutParams).weight
                    initialWeightCalendar =
                        (mCalendarContainer.layoutParams as LinearLayout.LayoutParams).weight
                }

                MotionEvent.ACTION_MOVE -> {
                    val deltaY = initialY - event.rawY
                    val totalHeight = mCalendarContainer.height + mFragmentContainer.height
                    val fragmentWeight = initialWeightFragment + deltaY / totalHeight
                    val calendarWeight = initialWeightCalendar - deltaY / totalHeight

                    if (fragmentWeight > 0 && calendarWeight > 0) {
                        (mFragmentContainer.layoutParams as LinearLayout.LayoutParams).weight =
                            fragmentWeight
                        (mCalendarContainer.layoutParams as LinearLayout.LayoutParams).weight =
                            calendarWeight

                        mFragmentContainer.requestLayout()
                        mCalendarContainer.requestLayout()
                    }
                }
            }
            return true
        }
    }
}
