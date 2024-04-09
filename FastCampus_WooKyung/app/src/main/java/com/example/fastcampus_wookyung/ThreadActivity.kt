package com.example.fastcampus_wookyung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class ThreadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

//        val currentThread = Thread.currentThread()
//        Log.d("testt", "" + currentThread)


        Thread {
            val currentThread = Thread.currentThread()
            findViewById<TextView>(R.id.threadTest).text = "changed"
            // UI관련 작업을 메인쓰레드가 아닌 쓰레드에서 하려고 하면 해당 작업은 메인쓰레드의 queue로 들어간다
            // -> 에러가 발생하지 않을 수도 있다

            // 이 작은 UI스레드에서 실행 시키겠다라는 명시적인 방법
            runOnUiThread{
                findViewById<TextView>(R.id.threadTest).text = "changed"
            }
        }.start()
    }
}