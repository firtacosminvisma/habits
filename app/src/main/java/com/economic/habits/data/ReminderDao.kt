package com.economic.habits.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert


/**
 * Created by cosmin on 1/26/18.
 * A DAO class to access the [Reminder] db
 */
@Dao
interface ReminderDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<Reminder>

    @Insert
    fun insertAll(vararg rem: Reminder)

    @Delete
    fun delete(rem: Reminder)

}