package com.economic.habits.ui.reminder

import android.arch.lifecycle.MutableLiveData
import com.economic.habits.base.BaseViewModel
import com.economic.habits.data.Reminder
import javax.inject.Inject

/**
 * Created by cosmin on 1/26/18.
 *
 */
class ReminderViewModel @Inject constructor(): BaseViewModel() {

    private val TAG = "ReminderViewModel"

    val state: MutableLiveData<Reminder> = MutableLiveData()

    private val rem = Reminder()

    init{
        state.postValue(rem)
    }

}