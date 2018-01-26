package com.economic.habits.ui.list

import android.arch.lifecycle.MediatorLiveData
import com.economic.habits.base.BaseViewModel
import com.economic.habits.data.Reminder
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by cosmin on 1/26/18.
 *
 */
class HabitsListViewModel @Inject constructor(
        private val model:HabitsListModel
) : BaseViewModel(), HabitsListEventListener {


    private val TAG = "HabitsListViewModel"
    val state:MediatorLiveData<HabitsListView> = MediatorLiveData()
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
                state.postValue(view)
            }
        } )

    }

    override fun fabClicked() {
        val rem = Reminder()
        rem.message = "some message added newlly"
        view.loading = true
        model.addReminder(rem)?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe {
                    view.loading = false
                    state.postValue(view)
                }
        state.postValue(view)
    }

}