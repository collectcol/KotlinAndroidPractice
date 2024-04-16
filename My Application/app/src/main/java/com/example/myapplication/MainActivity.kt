package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        val adapter = ChatRecyclerView(
            chatList,
            LayoutInflater.from(this@MainActivity)
        )

        with(findViewById<RecyclerView>(R.id.recyclerView)){
            this.adapter = adapter
        }

        findViewById<Button>(R.id.addButton).setOnClickListener {
            adapter.chatList.add(2, Chat("새로운 메시지", false))
            adapter.notifyItemInserted(2)
        }
    }
}

class Chat(
    val message: String,
    val is_right: Boolean
)

class ChatRecyclerView(
    val chatList: MutableList<Chat>,
    val inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class LeftMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val leftMessage: TextView

        init {
            leftMessage = itemView.findViewById<TextView>(R.id.leftMessage)
        }
    }

    class RightMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rightMessage: TextView

        init {
            rightMessage = itemView.findViewById<TextView>(R.id.rightMessage)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (chatList[position].is_right) {
            true -> 1
            false -> 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> RightMessageViewHolder(inflater.inflate(R.layout.rightchat, parent, false))
            else -> LeftMessageViewHolder(inflater.inflate(R.layout.leftchat, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = chatList[position]
        when (chat.is_right) {
            true -> (holder as RightMessageViewHolder).rightMessage.text =
                chatList[position].message

            false -> (holder as LeftMessageViewHolder).leftMessage.text = chatList[position].message
        }
    }
}