package com.my.doha

import android.os.Bundle
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

class MainActivity : BaseActivity(), View.OnClickListener, CalendarAdapter.ItemClickListener {
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView
    private lateinit var mToolbar: Toolbar
    private lateinit var mBottomNavigation: BottomNavigationView
    private lateinit var mCalendarContainer: View
    private lateinit var mFragmentContainer: View

//    private lateinit var mTextViewMonth: TextView

    // 컬린더뷰 어댑터
    private lateinit var mCalendarAdapter: CalendarAdapter
    private lateinit var mWeekAdapter: WeekAdapter

    // 요일 리스트
    private lateinit var mDayList: ArrayList<Day>
    private lateinit var mWeekList: ArrayList<String>

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

        mDohaAppContext = applicationContext as DohaApp
//        mTextViewMonth = findViewById(R.layout.textview)

        mDrawerLayout = findViewById(R.id.drawer_layout)
        mNavigationView = findViewById(R.id.navigation_view)
        mToolbar = findViewById(R.id.toolbar)
        mCalendarContainer = findViewById(R.id.calendar_container)
        mRecyclerViewWeekList = findViewById<RecyclerView?>(R.id.recyclerView_weeklist).apply {
            this.layoutManager = GridLayoutManager(this@MainActivity, 7)
            this.setItemViewCacheSize(7)
        }
        mRecyclerViewDayList = findViewById<RecyclerView?>(R.id.recyclerView_daylist).apply {
            this.layoutManager = GridLayoutManager(this@MainActivity, 7)
            this.setItemViewCacheSize(42)
        }
        setWeekListBar()
        setDayListView(getDayList(savedInstanceState))
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

    private fun getDayList(savedInstanceState: Bundle): Calendar {
        mCalendar = Calendar.getInstance()
        if (savedInstanceState != null) {
            mCalendar.set(
                savedInstanceState.getInt(YEAR), savedInstanceState.getInt(MONTH), 1
            )
        } else {
            mCalendar.set(Calendar.DAY_OF_MONTH, 1)
        }
        return mCalendar
    }

    override fun onResume() {
        super.onResume()
        mCalendar.set(Calendar.DAY_OF_MONTH, 1)
        setDayListView(mCalendar)
    }

    private fun setWeekListBar() {
        mDayList = ArrayList<String>().apply {
            add("일")
            add("월")
            add("화")
            add("수")
            add("목")
            add("금")
            add("토")
        }
        mWeekAdapter = WeekAdapter(this, mDayList)
        mRecyclerViewWeekList.adapter = mWeekAdapter

        mDayList = ArrayList<Day>()
    }

    private fun setDayListView(calendar: Calendar){

        var dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)
        var thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        var lastMonthStartDay = calendar.add(Calendar.MONTH, -1).let {
            calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        }

        lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        lastMonthStartDay -= calendar.add(Calendar.MONTH, 1).let{
            dayOfMonth - 1 - 1
        }

        mToolbar.title = (calendar.get(Calendar.MONTH) + 1).toString()

        mDayList.clear()
        var day: Day
        for (i in 0 until dayOfMonth - 1) {
            var date = lastMonthStartDay + i
            day = Day()
            day.setDay(date)
            day.setInMonth(false)

            mDayList.add(day)
        }

        for (i in 1 until 35 - (thisMonthLastDay + dayOfMonth) + 1) {
            day = Day()
            day.setDay(i)
        }
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

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}
