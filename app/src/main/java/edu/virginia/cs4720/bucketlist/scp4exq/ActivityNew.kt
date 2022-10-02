package edu.virginia.cs4720.bucketlist.scp4exq

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ActivityNew : AppCompatActivity() {

    lateinit var chooseDate: Button
    lateinit var chosenDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        chooseDate = findViewById(R.id.button2)
        chosenDate = findViewById(R.id.chooseDate)

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
 ***************************************************************************************/