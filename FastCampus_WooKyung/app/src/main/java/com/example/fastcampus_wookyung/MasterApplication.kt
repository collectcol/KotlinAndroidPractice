package com.example.fastcampus_wookyung

import android.app.Application
import android.util.Log

// 앱에서 계속 유지되어야 하는 정보는 Activity Context에 저장하지말고
// Application Context에 저장하는것이 더 좋다

// Application Context 를 추가하려면 Manifest파일에 name 속성에 이 클래스를 추가 하면 된다.
class MasterApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // 앱이 살아있는 동안에 계속 존재해야 하는것
        
    }
}