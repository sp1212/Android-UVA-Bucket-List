package edu.virginia.cs4720.bucketlist.scp4exq

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var db = DatabaseHandler(context)
    var data = db.readData().sortedBy { it.dueDate }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = data[position].title
        holder.itemDate.text = data[position].dueDate

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