package com.my.doha

import android.app.ActionBar
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.collection.LLRBNode
import java.util.Date

class RecyclerViewAdapterDay(
    val context: Context,
    val tempMonth: Int,
    val dayList: MutableList<Date>,
    val actionBar: ActionBar?
) : RecyclerView.Adapter<RecyclerViewAdapterDay.ViewHolder>() {
    val ROW = 6

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
            actionBar?.title = dayList[position].toString()
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
    }

    override fun getItemCount(): Int {
        return ROW * 7
    }
}