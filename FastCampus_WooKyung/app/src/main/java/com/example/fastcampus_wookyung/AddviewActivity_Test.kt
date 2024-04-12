package com.example.fastcampus_wookyung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat

class AddviewActivity_Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addview_test)

        var chatList = mutableListOf<ChatInfo>()
        chatList.add(ChatInfo("홍길동", "안녕하세요", "이순신", "네"))
        chatList.add(ChatInfo("홍길동", "뭐 하고 계세요?", "이순신", "공부하고 있어요"))
        chatList.add(ChatInfo("홍길동", "아 그러시구나", "이순신", "길동님은 뭐하시고 계세요?"))
        chatList.add(ChatInfo("홍길동", "저는 일 하고 있어요", "이순신", "일 힘들지 않아요?"))
        chatList.add(ChatInfo("홍길동", "괜찮아요", "이순신", "네네"))
        chatList.add(ChatInfo("홍길동", "몇시까지 하세요?", "이순신", "저는 저녁 7시까지에요"))
        chatList.add(ChatInfo("홍길동", "어 저도 7시까지에요", "이순신", "그렇구나"))
        chatList.add(ChatInfo("홍길동", "네", "이순신", "안녕히 가세요"))

        val container = findViewById<LinearLayoutCompat>(R.id.container)
        val inflater = LayoutInflater.from(this@AddviewActivity_Test)


        for (i in 0 until chatList.size) {
            val chatItemView = inflater.inflate(R.layout.chat_item1, null)

            val userName1 = chatItemView.findViewById<TextView>(R.id.userName1)
            val message1 = chatItemView.findViewById<TextView>(R.id.message1)
            val userName2 = chatItemView.findViewById<TextView>(R.id.userName2)
            val message2 = chatItemView.findViewById<TextView>(R.id.message2)

            userName1.text = chatList[i].userName1
            message1.text = chatList[i].message1
            userName2.text = chatList[i].userName2
            message2.text = chatList[i].message2
            container.addView(chatItemView)
        }
    }
}

class ChatInfo(
    val userName1: String,
    val message1: String,
    val userName2: String,
    val message2: String
)