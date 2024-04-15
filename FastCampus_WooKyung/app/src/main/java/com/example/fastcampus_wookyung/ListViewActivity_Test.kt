package com.example.fastcampus_wookyung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import java.util.zip.Inflater

class ListViewActivity_Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_test)

        var chatList = mutableListOf<ChatInfo>()
        chatList.add(ChatInfo("홍길동", "안녕하세요", "이순신", "네"))
        chatList.add(ChatInfo("홍길동", "뭐 하고 계세요?", "이순신", "공부하고 있어요"))
        chatList.add(ChatInfo("홍길동", "아 그러시구나", "이순신", "길동님은 뭐하시고 계세요?"))
        chatList.add(ChatInfo("홍길동", "저는 일 하고 있어요", "이순신", "일 힘들지 않아요?"))
        chatList.add(ChatInfo("홍길동", "괜찮아요", "이순신", "네네"))
        chatList.add(ChatInfo("홍길동", "몇시까지 하세요?", "이순신", "저는 저녁 7시까지에요"))
        chatList.add(ChatInfo("홍길동", "어 저도 7시까지에요", "이순신", "그렇구나"))
        chatList.add(ChatInfo("홍길동", "네", "이순신", "안녕히 가세요"))

        var adapter = ChatAdpater(
            chatList,
            LayoutInflater.from(this@ListViewActivity_Test)
        )
        var listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        // 리스너 장착 방법
        listView.setOnItemClickListener { parent, view, position, id ->
            var chatInfo = adapter.chatList[position]
            var a = chatInfo.userName1
            var b = chatInfo.message1
        }

        // 데이터 갱신 방법
        findViewById<TextView>(R.id.addlist).setOnClickListener {
            adapter.chatList.add(
                ChatInfo("홍길동", "네", "이순신", "안녕히 가세요")
            )
            adapter.notifyDataSetChanged() // 얘만 호출하면 갱신해준다
        }
    }
}

class ChatAdpater(
    val chatList: MutableList<ChatInfo>,
    val layoutInflater: LayoutInflater
) : BaseAdapter(){
    override fun getCount(): Int {
        return chatList.size
    }

    override fun getItem(position: Int): Any {
        return chatList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ChatListHolder

        if(convertView == null){
            view = layoutInflater.inflate(R.layout.chat_item1, null)
            holder = ChatListHolder()
            holder.userName1 = view.findViewById(R.id.userName1)
            holder.message1 = view.findViewById(R.id.message1)
            holder.userName2 = view.findViewById(R.id.userName2)
            holder.message2 = view.findViewById(R.id.message2)

            view.tag = holder
        }else {
            holder = convertView.tag as ChatListHolder
            view = convertView
        }
        var chatInfo = chatList[position]

        holder.userName1?.text = chatInfo.userName1
        holder.message1?.text = chatInfo.message1
        holder.userName2?.text = chatInfo.userName2
        holder.message2?.text = chatInfo.message2

        return view
    }

}

class ChatListHolder(
    var userName1: TextView? = null,
    var message1 : TextView? = null,
    var userName2: TextView? = null,
    var message2 : TextView? = null
)
