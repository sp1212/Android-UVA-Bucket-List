package edu.virginia.cs4720.bucketlist.scp4exq

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_new.*
import kotlinx.android.synthetic.main.activity_new.itemTitle
import kotlinx.android.synthetic.main.activity_new.submitButton
import java.util.*

class ActivityEdit : AppCompatActivity() {

    lateinit var chooseDate: Button
    lateinit var chosenDate: TextView
    lateinit var title: EditText
    lateinit var id: TextView
    lateinit var completionStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        chooseDate = findViewById(R.id.button2)
        chosenDate = findViewById(R.id.chooseDate)
        title = findViewById(R.id.itemTitle)
        id = findViewById(R.id.itemId)
        completionStatus = findViewById(R.id.completionStatus)


        chosenDate.text = intent.getStringExtra("dueDate").toString()
        title.setText(intent.getStringExtra("title").toString())
        id.text = intent.getStringExtra("id").toString()
        if (intent.getBooleanExtra("completed", false) === true) {
            completionStatus.text = "Completed " + intent.getStringExtra("completedDate").toString()
        } else {
            completionStatus.text = "Incomplete"
        }


        chooseDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val y = cal.get(Calendar.YEAR)
            val m = cal.get(Calendar.MONTH)
            val d = cal.get(Calendar.DAY_OF_MONTH)

            val dpg = DatePickerDialog(this,
                {
                        view, year, monthOfYear, dayOfMonth -> chosenDate.text =
                    (year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth)
                }, y, m, d)
            dpg.show()
        }

        val context = this

        submitButton.setOnClickListener {
            if (itemTitle.text.toString().isNotEmpty() && chosenDate.text.toString().isNotEmpty()
                && chosenDate.text.toString().compareTo("Due Date") !== 0) {


                var id = id.text.toString().toInt()
                var title = title.text.toString()
                var dueDate = chosenDate.text.toString()
                var db = DatabaseHandler(context)
                db.editItem(id, title, dueDate)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(context, "Please input a valid title and date.", Toast.LENGTH_SHORT).show()
            }
        }

        deleteButton.setOnClickListener {
            if (id.text.toString().toInt() >= 0) {
                var db = DatabaseHandler(context)
                db.deleteItem(id.text.toString().toInt())

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(context, "Unable to delete item.", Toast.LENGTH_SHORT).show()
            }
        }
        supportActionBar?.title = "UVA Bucket List"
    }

}

/***************************************************************************************
 *  REFERENCES
 *  Title: How to open a new activity with a button click - Android Kotlin Example
 *  Author: Simple Schwarz
 *  Date: October 6, 2021
 *  URL: https://medium.com/@simple.schwarz/how-to-open-a-new-activity-with-a-button-click-android-kotlin-example-bd2107946bbe
 *
 *  Title: DatePicker in Android
 *  Author: chaitanyamunje
 *  Date: 17 Jul, 2022
 *  URL: https://www.geeksforgeeks.org/datepicker-in-android/
 *
 *  Title: Android Tutorial (Kotlin) - 30 - SQLite Database Creation and Insertion
 *  Author: CodeAndroid
 *  Date: Dec 11, 2017
 *  URL: https://www.youtube.com/watch?v=OxHNcCXnxnE
 ***************************************************************************************/