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

    @Query( "SELECT * FROM reminders WHERE :now - minute < 0 ORDER BY :now - minute DESC LIMIT 2" )
    fun getFirstTwo(now: Int): List<Reminder>


    @Query( "SELECT * FROM reminders WHERE :now - minute <= 0 ORDER BY :now - minute DESC LIMIT 1" )
    fun getFirstAfter(now: Int): Reminder

    @Query("SELECT * FROM reminders WHERE minute = :min")
    fun getAllWithMin(min: Int): List<Reminder>
}
