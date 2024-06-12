package com.my.doha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar
import java.util.Date

class RecyclerViewAdapterMonth(
    var inflater: LayoutInflater,
    var calendar: Calendar
) : RecyclerView.Adapter<RecyclerViewAdapterMonth.ViewHolder>() {
    var center = Int.MAX_VALUE / 2
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.list_item_month, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        calendar.time = Date()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.MONTH, position - center)
        holder.
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}