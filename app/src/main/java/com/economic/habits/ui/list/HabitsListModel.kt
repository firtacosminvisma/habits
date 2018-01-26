package com.economic.habits.ui.list

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
class HabitsListModel @Inject constructor(private val reminderDao: ReminderDao): BaseModel() {

    fun getReminders():List<Reminder>{
        return listOf(Reminder().apply {
            message = "remimnder 1"
            minute = 20
        }, Reminder().apply {
            message = "reminger 2"
            minute = 50
        })
    }


    fun addReminder(rem: Reminder): Observable<Boolean>? {
        return Observable.fromCallable(InsertCallable(rem, reminderDao))
    }

    fun getReminderLiveData():LiveData<List<Reminder>>{
        return reminderDao.getAll()
    }

    class InsertCallable(
        var remToAdd:Reminder? = null,
        var reminderDAO:ReminderDao
    ):Callable<Boolean>{
        override fun call(): Boolean {
            remToAdd?.let{
                reminderDAO.insertAll(it)
            }
            return true
        }
    }

}