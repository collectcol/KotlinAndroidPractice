package com.my.doha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewDayListAdapter(
    var dayList: MutableList<String>,
    var inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerViewDayListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day: TextView

        init {
            day = itemView.findViewById(R.id.textView_day)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.calendar_item_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.day.text = dayList[position]
    }

    override fun getItemCount(): Int {
        return dayList.size
    }
}