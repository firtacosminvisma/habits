package com.economic.habits.ui.model

import android.arch.lifecycle.LiveData
import com.economic.habits.base.BaseModel
import com.economic.habits.data.Reminder
import com.economic.habits.data.ReminderDao
import io.reactivex.Observable
import org.joda.time.DateTime
import java.util.concurrent.Callable
import javax.inject.Inject

/**
 * Created by cosmin on 1/26/18.
 *
 */
class ReminderModel @Inject constructor(private val reminderDao: ReminderDao) : BaseModel() {

    fun addReminder(rem: Reminder): Observable<Boolean>? {
        return Observable.fromCallable(InsertCallable(rem, reminderDao))
    }

    fun getReminderLiveData(): LiveData<List<Reminder>> {
        return reminderDao.getAll()
    }

    fun removeReminder(rem:Reminder): Observable<Boolean> {
        return Observable.fromCallable(RemoveCallable(rem, reminderDao))
    }

    fun getFirstTwoReminders(): List<Reminder> {
        return reminderDao.getFirstTwo(DateTime.now().minuteOfDay)
    }

    fun getFirst(): Reminder {
        return reminderDao.getFirstAfter(DateTime.now().minuteOfDay)
    }

    fun getAllIdenticalAfter(minute: Int): List<Reminder> {
        val first = getFirstAfter(minute)
        return getAllWithMin(first.minute)
    }

    fun getFirstAfter(minute: Int): Reminder {
        return reminderDao.getFirstAfter(minute)
    }

    fun getAllWithMin(minute: Int): List<Reminder> {
        return reminderDao.getAllWithMin(minute)

    }

    class InsertCallable(
        private var remToAdd:Reminder? = null,
        private var reminderDAO:ReminderDao
    ):Callable<Boolean> {
        override fun call(): Boolean {
            remToAdd?.let{
                reminderDAO.insertAll(it)
            }
            return true
        }
    }

    class RemoveCallable(
            private var remToAdd:Reminder? = null,
            private var reminderDAO:ReminderDao
    ):Callable<Boolean> {
        override fun call(): Boolean {
            remToAdd?.let{
                reminderDAO.delete(it)
            }
            return true
        }
    }

}