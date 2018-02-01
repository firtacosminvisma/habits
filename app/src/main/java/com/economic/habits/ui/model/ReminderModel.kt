package com.economic.habits.ui.model

import android.arch.lifecycle.LiveData
import com.economic.habits.base.BaseModel
import com.economic.habits.data.Reminder
import com.economic.habits.data.ReminderDao
import io.reactivex.Observable
import java.util.concurrent.Callable
import javax.inject.Inject

/**
 * Created by cosmin on 1/26/18.
 *
 */
class ReminderModel @Inject constructor(private val reminderDao: ReminderDao): BaseModel() {

    fun addReminder(rem: Reminder): Observable<Boolean>? {
        return Observable.fromCallable(InsertCallable(rem, reminderDao))
    }

    fun getReminderLiveData():LiveData<List<Reminder>>{
        return reminderDao.getAll()
    }

    class InsertCallable(
        private var remToAdd:Reminder? = null,
        private var reminderDAO:ReminderDao
    ):Callable<Boolean>{
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
    ):Callable<Boolean>{
        override fun call(): Boolean {
            remToAdd?.let{
                reminderDAO.delete(it)
            }
            return true
        }
    }


    fun removeReminder(rem:Reminder):Observable<Boolean> {
        return Observable.fromCallable(RemoveCallable(rem, reminderDao))
    }

}