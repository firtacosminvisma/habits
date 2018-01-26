package com.economic.habits.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*


/**
 * Created by cosmin on 1/26/18.
 * A DAO class to access the [Reminder] db
 */
@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminders")
    fun getAll(): LiveData<List<Reminder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg rem: Reminder)

    @Delete
    fun delete(rem: Reminder)

}