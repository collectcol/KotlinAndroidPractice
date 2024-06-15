package com.my.doha

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar
import java.util.Date

class RecyclerViewAdapterMonth(
    var context: Context,
    var calendar: Calendar
) : RecyclerView.Adapter<RecyclerViewAdapterMonth.ViewHolder>() {
    var center = 100

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextViewMonth: TextView
        var mRecyclerViewDayList: RecyclerView

        init {
            mTextViewMonth = itemView.findViewById(R.id.item_month_text)
            mRecyclerViewDayList = itemView.findViewById(R.id.item_month_day_list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_month, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        calendar.time = Date()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.MONTH, position - center)
        holder.mTextViewMonth.text =
            "${calendar.get(Calendar.YEAR)}년 ${calendar.get(Calendar.MONTH) + 1}월"
        val tempMonth = calendar.get(Calendar.MONTH)

        var dayList: MutableList<Date> = mutableListOf()
        val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK) - 1

        calendar.add(Calendar.DAY_OF_MONTH, - firstDayOfMonth)
        for (i in 0 until 42) {
            dayList.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        holder.mRecyclerViewDayList.apply {
            layoutManager = GridLayoutManager(context, 7)
            adapter = RecyclerViewAdapterDay(context, tempMonth, dayList)
        }
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}