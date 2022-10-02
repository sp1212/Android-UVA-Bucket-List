package edu.virginia.cs4720.bucketlist.scp4exq

class BucketItem {

    var id: Int = 0
    var title : String = ""
    var dueDate : String = ""
    var completed : Boolean = false
    var completedDate : String = ""

    constructor(title:String, dueDate:String) {
        this.id = 0
        this.title = title
        this.dueDate = dueDate
        this.completed = false
        this.completedDate = ""
    }

}

/***************************************************************************************
 *  REFERENCES
 *  Title: Android Tutorial (Kotlin) - 30 - SQLite Database Creation and Insertion
 *  Author: CodeAndroid
 *  Date: Dec 11, 2017
 *  URL: https://www.youtube.com/watch?v=OxHNcCXnxnE
 ***************************************************************************************/