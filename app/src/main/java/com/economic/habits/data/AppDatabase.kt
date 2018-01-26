package com.economic.habits.data

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database



/**
 * Created by cosmin on 1/26/18.
 * The [RoomDatabase] object for the the app storage
 */
@Database(entities = arrayOf(Reminder::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): ReminderDao
}