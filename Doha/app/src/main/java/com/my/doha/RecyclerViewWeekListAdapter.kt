package com.my.doha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewWeekListAdapter(
    var weekList: MutableList<String>,
    var inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerViewWeekListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val week: TextView

        init {
            week = itemView.findViewById(R.id.textView_week)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewWeekListAdapter.ViewHolder {
        val view = inflater.inflate(R.layout.calendar_item_week, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewWeekListAdapter.ViewHolder, position: Int) {
        holder.week.text = weekList[position]
    }

    override fun getItemCount(): Int {
        return weekList.size
    }
}