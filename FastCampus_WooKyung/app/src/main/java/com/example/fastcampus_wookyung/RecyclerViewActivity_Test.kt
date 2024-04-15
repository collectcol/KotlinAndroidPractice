package com.example.fastcampus_wookyung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        with(findViewById<RecyclerView>(R.id.recyclerView_test)){
            this.layoutManager = LinearLayoutManager(this@RecyclerViewActivity_Test)
            this.adapter = ChatRecyclerAdpater(
                chatList = chatList,
                inflater = LayoutInflater.from(this@RecyclerViewActivity_Test)
            )
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
): RecyclerView.Adapter<ChatRecyclerAdpater.ChatViewHolder>(){
    inner class ChatViewHolder(itemView :View): RecyclerView.ViewHolder(itemView){

        val leftMessageText: TextView
        val rightMessageText: TextView

        init {
            leftMessageText = itemView.findViewById(R.id.leftMessage)
            rightMessageText = itemView.findViewById(R.id.rightMessage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        var view = inflater.inflate(R.layout.chat_item2, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.leftMessageText.text = chat.message

    }
}



