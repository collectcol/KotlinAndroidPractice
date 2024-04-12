package com.example.fastcampus_wookyung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity_Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_test)

        var chatList = mutableListOf<ChatInfo>()
        chatList.add(ChatInfo("홍길동", "안녕하세요", "이순신", "네"))
        chatList.add(ChatInfo("홍길동", "뭐 하고 계세요?", "이순신", "공부하고 있어요"))
        chatList.add(ChatInfo("홍길동", "아 그러시구나", "이순신", "길동님은 뭐하시고 계세요?"))
        chatList.add(ChatInfo("홍길동", "저는 일 하고 있어요", "이순신", "일 힘들지 않아요?"))
        chatList.add(ChatInfo("홍길동", "괜찮아요", "이순신", "네네"))
        chatList.add(ChatInfo("홍길동", "몇시까지 하세요?", "이순신", "저는 저녁 7시까지에요"))
        chatList.add(ChatInfo("홍길동", "어 저도 7시까지에요", "이순신", "그렇구나"))
        chatList.add(ChatInfo("홍길동", "네", "이순신", "안녕히 가세요"))

        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
    }
}

class ChatRecyclerViewAdapter(
    var chatList: MutableList<ChatInfo>,
    var inflater: LayoutInflater
) : RecyclerView.Adapter<ChatRecyclerViewAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName1: TextView
        val message1: TextView
        val userName2: TextView
        val message2: TextView

        init {
            userName1 = itemView.findViewById(R.id.userName1)
            message1 = itemView.findViewById(R.id.message1)
            userName2 = itemView.findViewById(R.id.userName2)
            message2 = itemView.findViewById(R.id.message2)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatRecyclerViewAdapter.ChatViewHolder {
        // 아이템 뷰를 리턴
        val view = inflater.inflate(R.layout.chat_item1, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatRecyclerViewAdapter.ChatViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

