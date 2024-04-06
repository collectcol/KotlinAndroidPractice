package com.example.fastcampus_wookyung

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class Intent_One : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_one)

        // 암시적 인텐트
        // - 전화, SMS, Google Play Store, Website, GoogleMap, 사진첩 등등
        // - 상수
        //      - 변하지 않는 수(문자)
        //      - 상수의 변수명은 전부 대문자로 만드는 문화가 있다
        // - URI (Uniform Resource Indentifier)
        //      - id라고 생각을 하면 된다 -> 고유하다
        //      - 자원을 나타내는 주소
        //      - URL
        //          - 인터넷 페이지의 고유의 주소
        val implicit_intent: TextView = findViewById(R.id.implicit_intent)
        implicit_intent.setOnClickListener {
            val intent: Intent = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:" + "010-3937-4885")
            )
            startActivity(intent)
        }

        // 명시적 인텐트 + ComponentName -> 액티비티 전환
        val intent_one: TextView = findViewById(R.id.intent1)
        intent_one.setOnClickListener {
            val intent: Intent = Intent()
            val componentName: ComponentName = ComponentName(
                "com.example.fastcampus_wookyung",
                "com.example.fastcampus_wookyung.Intent_Two"
            )
            intent.component = componentName
            startActivity(intent)
        }

        // 명시적 인텐트 -> 액티비티 전환
        // Context
        // - 문맥
        // A액티비티 -> B액티비티
        (findViewById<TextView>(R.id.intent2)).apply {
            this.setOnClickListener {
                startActivity(
                    // this의 구분을 해주기 위해 뒤에 @를 붙이고 Intent_One을 지정
                    Intent(this@Intent_One, Intent_Two::class.java)
                )
            }
        }
        // 명시적 인텐트 + data 전달
        (findViewById<TextView>(R.id.intent3)).apply {
            this.setOnClickListener {
                // this의 구분을 해주기 위해 뒤에 @를 붙이고 Intent_One을 지정
                val intent = Intent(this@Intent_One, Intent_Two::class.java)
                intent.putExtra("extra-data", "data-one")
                startActivity(intent)
            }
        }
        // 명시적 인텐트 + 결과 받기
        // requestCode
        // - 구분을 하기 위해서
        // - Intent_One -> Intent_Two (request 1)
        // - Intent_One -> Intent_Three (request 2)
        // - Intent_One -> Intent_Four (request 3)
        (findViewById<TextView>(R.id.intent4)).apply {
            this.setOnClickListener {
                // this의 구분을 해주기 위해 뒤에 @를 붙이고 Intent_One을 지정
                val intent = Intent(this@Intent_One, Intent_Two::class.java)
                startActivityForResult(intent, 1) // deprecated 되었다
            }
        }
        // 명시적 인텐트 + 결과받기 (ActivityResult API)
        // - request가 존재하지 않는다
        // -> requestCode 없이도 요청자를 구분할 수 있다
        val startActivityLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            // onActivityResult에 해당하는 부분
            // 각각의 intent 들이 처리되는 곳이 있다. (아래부분에서 처리된다) 그래서 구분이 필요없다
            when(it.resultCode){
                RESULT_OK -> {
                    Log.d("dataa", it.data?.extras?.getString("result")!!)
                }
            }
            // onActivityResult
            // - 모든 intent가 한 곳에서 처리된다 -> 구분이 필요하다(request code)
            // ActivitiResult API
            // - 각각의 intent가 처리되는 곳이 별도로 있다 -> 구분이 필요없다
        }

        (findViewById<TextView>(R.id.intent5)).apply {
            this.setOnClickListener {
                // this의 구분을 해주기 위해 뒤에 @를 붙이고 Intent_One을 지정
                val intent = Intent(this@Intent_One, Intent_Two::class.java)
                startActivityLauncher.launch(intent)
            }
        }
        // 명시적 인텐트 + 이미지 URI 전달
        (findViewById<TextView>(R.id.intent6)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java).apply {
                    val imageUri =
                        Uri.parse("android.resource://" + packageName + "/drawable/" + "dog_image")
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_STREAM, imageUri)
                    this.setType("image/*")
                }
                startActivity(intent)
            }
        }

        // 인텐트를 이용해서 데이터 전달이 가능하다
        // - 인텐트를 이용해서 키벨류 데이터를 전달한다
        // - 인텐트를 이용해서 이미지를 전달한다
        // ...
        // ... intent 활용법이 엄청 많다
    }

    // 결과를 받는 부분
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // resultCode (Status Code)
        // - 최종결과
        // - 성공, 실패
        when(requestCode){
            1-> {
                when(resultCode){
                    RESULT_OK -> {
                        val data:String? = data?.extras?.getString("result")
                        Log.d("dataa", data!!)
                    }
                }
            }
            2 -> {

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}