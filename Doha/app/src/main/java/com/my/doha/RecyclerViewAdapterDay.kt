package com.my.doha

import android.app.ActionBar
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.collection.LLRBNode
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class RecyclerViewAdapterDay(
    val context: Context,
    val tempMonth: Int,
    val dayList: MutableList<Date>,
) : RecyclerView.Adapter<RecyclerViewAdapterDay.ViewHolder>() {
    private val ROW = 6
//    private val dateFormat = SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault())
    private val today = Calendar.getInstance().apply {
        // 시간을 0으로 설정하여 날짜만 비교
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time

    inner class ViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        var mItemDayLayout: LinearLayout
        var mTextViewDay: TextView

        init {
            mItemDayLayout = viewItem.findViewById(R.id.item_day_layout)
            mTextViewDay = viewItem.findViewById(R.id.item_day_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItemDayLayout.setOnClickListener {
//            val formattedDate = dateFormat.format(dayList[position])
//            DohaApp.actionBar?.title = formattedDate
        }
        holder.mTextViewDay.text = dayList[position].date.toString()

        holder.mTextViewDay.setTextColor(
            when (position % 7) {
                0 -> Color.RED
                6 -> Color.BLUE
                else -> Color.BLACK
            }
        )

        if (tempMonth != dayList[position].month) {
            holder.mTextViewDay.alpha = 0.4f
        }

        // 오늘 날짜 강조
        val calendar = Calendar.getInstance().apply {
            time = dayList[position]
            // 시간을 0으로 설정하여 날짜만 비교
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        if (calendar.time == today) {
            holder.mTextViewDay.setBackgroundColor(Color.YELLOW)
        } else {
            holder.mTextViewDay.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    override fun getItemCount(): Int {
        return ROW * 7
    }
}