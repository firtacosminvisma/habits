package com.economic.habits.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by cosmin on 1/26/18.
 * a pojo class holding a reminder
 */
@Entity(tableName = "reminders")
class Reminder {
    @PrimaryKey(autoGenerate = true)
    var uid:Int = 0

    @ColumnInfo(name = "message")
    var message:String = ""

    @ColumnInfo(name= "minute")
    var minute:Long = 0

}