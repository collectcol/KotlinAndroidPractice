package com.example.fastcampus_wookyung

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

// 앱에서 계속 유지되어야 하는 정보는 Activity Context에 저장하지말고
// Application Context에 저장하는것이 더 좋다

// Application Context 를 추가하려면 Manifest파일에 name 속성에 이 클래스를 추가 하면 된다.
class MasterApplication : Application() {

    val userId = 1000
    override fun onCreate() {
        super.onCreate()

        // 모든 Activity의 라이프 사이클 관찰
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.d("testt", "onActivityCreated")
            }

            override fun onActivityStarted(activity: Activity) {
                Log.d("testt", "onActivityCreated")            }

            override fun onActivityResumed(activity: Activity) {
                Log.d("testt", "onActivityResumed")            }

            override fun onActivityPaused(activity: Activity) {
                Log.d("testt", "onActivityPaused")            }

            override fun onActivityStopped(activity: Activity) {
                Log.d("testt", "onActivityStopped")            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Log.d("testt", "onActivitySaveInstanceState")            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.d("testt", "onActivityDestroyed")            }

        })

        // 앱이 살아있는 동안에 계속 존재해야 하는것
    }

    fun methodFromApplication(){
        Log.d("testt","methodFromApplication" )
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}