package edu.virginia.cs4720.bucketlist.scp4exq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var titles = arrayOf("Task 1", "Task 2", "Task 3", "Task 4", "Task 5")
    private var dates = arrayOf("11/16/2000", "01/16/1966", "07/27/1966", "09/29/2022", "09/30/2022")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDate.text = dates[position]

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView
        var itemDate: TextView
        var itemCheck: CheckBox

        init {
            itemCheck = itemView.findViewById(R.id.checkBox)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDate = itemView.findViewById(R.id.item_date)
        }
    }

}

/***************************************************************************************
 *  REFERENCES
 *  Title: RecyclerView in Android Studio [Kotlin 2020]
 *  Author: CodeWithMazn
 *  Date: September 10, 2020
 *  URL: https://www.youtube.com/watch?v=UCddGYMQJCo
 ***************************************************************************************/