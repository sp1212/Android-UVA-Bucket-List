package edu.virginia.cs4720.bucketlist.scp4exq

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(var context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var db = DatabaseHandler(context)
    var allData = db.readData()
    var notCompletedData = allData.filter { it.completed === false }.sortedBy { it.dueDate }
    var completedData = allData.filter { it.completed === true }.sortedBy { it.completedDate }
    var data = notCompletedData.plus(completedData)

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
        holder.itemId.text = data[position].id.toString()

        holder.itemCheck.isChecked = data[position].completed === true

        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, ActivityEdit::class.java)
            intent.putExtra("title", data[position].title)
            intent.putExtra("dueDate", data[position].dueDate)
            intent.putExtra("id", data[position].id.toString())
            intent.putExtra("completed", data[position].completed)
            intent.putExtra("completedDate", data[position].completedDate)
            view.context.startActivity(intent)
        }

        holder.itemCheck.setOnCheckedChangeListener { view, checked ->
            if (holder.itemCheck.isChecked === true) {
                db.changeCompleted(data[position].id, true)
                val intent = Intent(view.context, MainActivity::class.java)
                view.context.startActivity(intent)
            } else {
                db.changeCompleted(data[position].id, false)
                val intent = Intent(view.context, MainActivity::class.java)
                view.context.startActivity(intent)
            }
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView
        var itemDate: TextView
        var itemCheck: CheckBox
        var itemId: TextView

        init {
            itemCheck = itemView.findViewById(R.id.checkBox)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDate = itemView.findViewById(R.id.item_date)
            itemId = itemView.findViewById(R.id.itemId)
        }
    }

}

/***************************************************************************************
 *  REFERENCES
 *  Title: RecyclerView in Android Studio [Kotlin 2020]
 *  Author: CodeWithMazn
 *  Date: September 10, 2020
 *  URL: https://www.youtube.com/watch?v=UCddGYMQJCo
 *
 *  Title: How to open a different activity on recyclerView item onclick
 *  Author: Milad Moosavi
 *  Date: Nov 18, 2017
 *  URL: https://stackoverflow.com/questions/28767413/how-to-open-a-different-activity-on-recyclerview-item-onclick
 ***************************************************************************************/