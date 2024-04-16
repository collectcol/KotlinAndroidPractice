package com.example.fastcampus_wookyung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class RecyclerViewActivity_Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_test)

        var chatList = mutableListOf<Chat>()
        chatList.add(Chat("안녕하세요", false))
        chatList.add(Chat("안녕하세요", true))
        chatList.add(Chat("안녕하세요", false))
        chatList.add(Chat("안녕하세요", true))
        chatList.add(Chat("안녕하세요", false))
        chatList.add(Chat("안녕하세요", false))
        chatList.add(Chat("안녕하세요", false))
        chatList.add(Chat("안녕하세요", true))
        chatList.add(Chat("안녕하세요", false))
        chatList.add(Chat("안녕하세요", false))
        chatList.add(Chat("안녕하세요", true))

        var adapter = ChatRecyclerAdpater(
            chatList = chatList,
            inflater = LayoutInflater.from(this@RecyclerViewActivity_Test)
        )

        with(findViewById<RecyclerView>(R.id.recyclerView_test)) {
            this.layoutManager = LinearLayoutManager(this@RecyclerViewActivity_Test)
            this.adapter = adapter
        }
        findViewById<Button>(R.id.button).setOnClickListener {
            adapter.chatList.add(2, Chat("두번재입니다", false))
            // 어떻게 변했는지 구체적으로 적어주면된다.
            // adapter.notifyItemChanged... 등등
            adapter.notifyItemInserted(2)
        }
    }
}

class Chat(
    val message: String,
    val is_right: Boolean
)

class ChatRecyclerAdpater(
    val chatList: MutableList<Chat>,
    val inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        val leftMessageText: TextView
//        val rightMessageText: TextView
//
//        init {
//            leftMessageText = itemView.findViewById(R.id.leftMessage)
//            rightMessageText = itemView.findViewById(R.id.rightMessage)
//        }
//    }
    inner class RightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var rightMessageText: TextView

        init {
            rightMessageText = itemView.findViewById(R.id.rightMessage)
        }
    }

    inner class LeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var leftMessageText: TextView

        init {
            leftMessageText = itemView.findViewById(R.id.leftMessage)
        }
    }

    // onCreateViewHolder의 viewType을 반환한다
    // position에 따라서 viewType을 구분할 수 있게 만들어 주는 함수
    override fun getItemViewType(position: Int): Int {
        return when (chatList[position].is_right) {
            true -> 1
            else -> 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                RightViewHolder(inflater.inflate(R.layout.chat_item2, parent, false))
            }

            else -> {
                LeftViewHolder(inflater.inflate(R.layout.chat_item3, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = chatList[position]
        when (chat.is_right){
            true -> (holder as RightViewHolder).rightMessageText.text = chat.message
            false -> (holder as LeftViewHolder).leftMessageText.text = chat.message
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}



