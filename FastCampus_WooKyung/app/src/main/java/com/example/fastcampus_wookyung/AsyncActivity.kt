package com.example.fastcampus_wookyung

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView

class AsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        // AsyncTask 의 단점은 한번 취소되면 다시 실행을 못한다
        // 다시 객체를 생성해야 한다
        val backgroundTask = BackgroundAsyncTask(
            findViewById(R.id.progressBar),
            findViewById(R.id.progressBarText)
        )

        (findViewById<TextView>(R.id.start)).setOnClickListener {
            backgroundTask.execute()
        }

        (findViewById<TextView>(R.id.stop)).setOnClickListener {
            backgroundTask.cancel(true)
        }
    }
}

class BackgroundAsyncTask(
    val progressbar: ProgressBar,
    val progressText: TextView
) : AsyncTask<Int, Int, Int>() {
    // Params, Progress, Result
    // Params -> doInbackground 에서 사용할 타입
    // Progress -> onProgressUpdate에서 사용할 타입
    // Result -> onPostExcute에서 사용할 타입
    // deprecated -> 더 이상 사용을 권장하지 않는다
    // 코루틴 -> 멀티 태스킹, 동기 / 비동기 처리

    var percent: Int = 0
    override fun doInBackground(vararg params: Int?): Int {
        while (isCancelled() == false) {
            percent++
            if (percent > 100) break
            else {
                publishProgress(percent)
            }
            Thread.sleep(100)
        }
        return percent
    }

    // 우리가 만든 background 작업을 실행하기 전에 실행됨
    override fun onPreExecute() {
        percent = 0
        progressbar.setProgress(percent)
    }

    // 우리가 만든 background 작업을 다 실행되고 나서 실행됨, UI작업도 가능
    override fun onPostExecute(result: Int?) {
        progressText.text = "작업이 완료되었습니다."
    }

    // 진행중에 업데이트 하고 싶은 부분이 있으면 여기다가 작성
    override fun onProgressUpdate(vararg values: Int?) {
        progressbar.setProgress(values[0] ?: 0)
        progressText.text = "퍼센트 : " + values[0]
        super.onProgressUpdate(*values)
    }

    // background작업이 멈췄을때 하고 싶은 작업
    override fun onCancelled(result: Int?) {
        progressbar.setProgress(0)
        progressText.text = "작업이 취소되었습니다."
    }
}