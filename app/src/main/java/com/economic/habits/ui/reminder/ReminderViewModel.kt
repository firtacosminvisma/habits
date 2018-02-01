package com.economic.habits.ui.reminder

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.economic.habits.base.BaseViewModel
import com.economic.habits.data.Reminder
import com.economic.habits.ui.model.ReminderModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by cosmin on 1/26/18.
 *
 */
class ReminderViewModel @Inject constructor(private var model: ReminderModel): BaseViewModel(),ReminderEventListener {

    private val TAG = "ReminderViewModel"

    companion object {
        val UPDATE_UI = "UPDATE_UI"
        val CLOSE_ACT = "CLOSE_ACT"

    }

    val state: MutableLiveData<String> = MutableLiveData()
    val view = ReminderView()

    private val rem = Reminder()


    init{
        state.postValue(UPDATE_UI)
    }
    override fun saveClicked() {
        Log.d(TAG, "saveClicked:: message: ${view.message} time: ${view.minutes}")
        if ( view.message.isNotEmpty() && view.minutes > 0 )
        {
            rem.message = view.message
            rem.minute = view.minutes

            model.addReminder(rem)?.subscribeOn(Schedulers.newThread())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe {
                        state.postValue(CLOSE_ACT)
                    }
        }
    }


}