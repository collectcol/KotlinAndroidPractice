package com.my.doha

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.my.doha.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
    private lateinit var mCheckBoxSchedule: CheckBox
    private lateinit var mCheckBoxAccountBook: CheckBox
    private lateinit var mCheckBoxNote: CheckBox
    private lateinit var mCheckBoxDiary: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDohaAppContext = DohaApp.applicationContext()
        mDohaAppInstance = mDohaAppContext as DohaApp

        mDrawerLayout = findViewById(R.id.drawer_layout)
        mNavigationView = findViewById(R.id.navigation_view)
        mToolbar = findViewById<Toolbar?>(R.id.toolbar).apply {
            title = ""
        }
        mCalendarContainer = findViewById(R.id.calendar_container)
        mCalendarCustom = findViewById<RecyclerView?>(R.id.calendar_custom).apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = RecyclerViewAdapterMonth(this@MainActivity, mDohaAppInstance.mCalendar)
//            scrollToPosition(Int.MAX_VALUE / 2)
            scrollToPosition(100)

            LinearSnapHelper().attachToRecyclerView(this)

            // 스크롤 상태 리스너 추가 (한달 단위로 캘린더 움직임)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
                        val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                        val middlePosition = (firstVisiblePosition + lastVisiblePosition) / 2
                        recyclerView.smoothScrollToPosition(middlePosition)
                    }
                }
            })
        }

        mFragmentContainer = findViewById(R.id.fragment_container)

        setSupportActionBar(mToolbar)
        DohaApp.actionBar = supportActionBar

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

        // 오늘 날짜로 ActionBar 타이틀 설정
//        val today = Calendar.getInstance().time
//        val dateFormat = SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault())
//        DohaApp.actionBar?.title = dateFormat.format(today)

        // 데이터 클래스 (CalendarData)와 매칭된 데이터 리스트
//        val matchingDataForSchedule: List<CalendarData> = // 스케줄에 매칭된 데이터 리스트 가져오기
//        val matchingDataForAccountBook: List<CalendarData> = // 가계부에 매칭된 데이터 리스트 가져오기
//        val matchingDataForNote: List<CalendarData> = // 메모에 매칭된 데이터 리스트 가져오기
//        val matchingDataForDiary: List<CalendarData> = // 일기에 매칭된 데이터 리스트 가져오기

        // 툴바에서 체크박스 가져오기
        mCheckBoxSchedule = findViewById<CheckBox?>(R.id.checkBox_schedule).apply {
            setupCheckBoxListener(this)
        }
        mCheckBoxAccountBook = findViewById<CheckBox?>(R.id.checkBox_account_book).apply {
            setupCheckBoxListener(this)
        }
        mCheckBoxNote = findViewById<CheckBox?>(R.id.checkBox_note).apply {
            setupCheckBoxListener(this)
        }
        mCheckBoxDiary = findViewById<CheckBox?>(R.id.checkBox_diary).apply {
            setupCheckBoxListener(this)
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

//    private fun setupCheckBoxListener(checkBox: CheckBox, matchingData: List<CalendarData>) {
//        checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
//            // 체크박스 상태에 따른 처리 로직 추가
//            if (isChecked) {
//                // 체크되었을 때의 동작 : 매칭된 데이터를 캘린더에 표시
//                for (data in matchingData) {
//
//                }
//            } else {
//                // 체크 해제되었을 때의 동작 : 캘린더에서 해당 데이터 화면에 표시 안 함
//                for (data in matchingData) {
//
//                }
//            }
//        }
//    }

    // 차후 위의 메서드로 수정 -> 빌드오류를 방지하기위해 밑에 기능 임시적으로 사용
    private fun setupCheckBoxListener(checkBox: CheckBox) {
        checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->

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
