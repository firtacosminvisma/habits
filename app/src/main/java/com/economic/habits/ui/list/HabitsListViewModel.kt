package com.economic.habits.ui.list

import android.arch.lifecycle.MediatorLiveData
import android.util.Log
import com.economic.habits.base.BaseViewModel
import com.economic.habits.data.Reminder
import com.economic.habits.ui.model.ReminderModel
import com.economic.habits.ui.reminder.ReminderViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by cosmin on 1/26/18.
 *
 */
class HabitsListViewModel @Inject constructor(
        private val model: ReminderModel
) : BaseViewModel(), HabitsListEventListener {

    companion object {
        val UPDATE_UI = "UPDATE_UI"
        val GO_TO_NEW_REMINDER = "GO_TO_NEW_REMINDER"
    }


    private val TAG = "HabitsListViewModel"
    val state:MediatorLiveData<String> = MediatorLiveData()
    val view:HabitsListView = HabitsListView()


    init{
//        view.reminders = model.getReminders()
//        view.loading = false
//        state.postValue(view)

        state.addSource(model.getReminderLiveData(),{ list ->
            list?.let {
                view.reminders.clear()
                view.reminders.addAll(it.asIterable())
                view.loading = false
                state.postValue(UPDATE_UI)
            }
        } )

    }

    fun removeReminder(rem: Reminder){
        model.removeReminder(rem).subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe {
//                    state.postValue(ReminderViewModel.CLOSE_ACT)
                    Log.d(TAG,"::removed reminder")
                }
    }

    override fun fabClicked() {
//        val rem = Reminder()
//        rem.message = "some message added newlly"
//        view.loading = true
//        model.addReminder(rem)?.subscribeOn(Schedulers.newThread())
//                ?.observeOn(AndroidSchedulers.mainThread())
//                ?.subscribe {
//                    view.loading = false
//                    state.postValue(UPDATE_UI)
//                }
//        state.postValue(UPDATE_UI)
        state.postValue(GO_TO_NEW_REMINDER)
    }

}