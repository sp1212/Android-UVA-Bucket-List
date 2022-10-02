package edu.virginia.cs4720.bucketlist.scp4exq

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyDatabase"
val TABLE_NAME = "Items"
val COL_ID = "id"
val COL_TITLE = "title"
val COL_DUEDATE = "dueDate"
val COL_COMPLETED = "completed"
val COL_COMPLETEDDATE = "completedDate"

class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_TITLE + " VARCHAR(256)," +
                COL_DUEDATE + " VARCHAR(256)," +
                COL_COMPLETED + " INTEGER," +
                COL_COMPLETEDDATE + " VARCHAR(256))";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(item : BucketItem) {
        val db = this.writableDatabase

        var cv = ContentValues()
        cv.put(COL_TITLE, item.title)
        cv.put(COL_DUEDATE, item.dueDate)
        cv.put(COL_COMPLETED, item.completed)
        cv.put(COL_COMPLETEDDATE, item.completedDate)

        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Item creation failure.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Item created successfully.", Toast.LENGTH_SHORT).show()
        }
    }
}

/***************************************************************************************
 *  REFERENCES
 *  Title: Android Tutorial (Kotlin) - 30 - SQLite Database Creation and Insertion
 *  Author: CodeAndroid
 *  Date: Dec 11, 2017
 *  URL: https://www.youtube.com/watch?v=OxHNcCXnxnE
 ***************************************************************************************/