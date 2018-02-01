package com.economic.habits.ui.list.list

import com.economic.habits.data.Reminder
import com.economic.habits.ui.base.ItemViewModel
import com.economic.habits.ui.list.HabitsListViewModel
import com.economic.habits.ui.model.ReminderModel
import com.economic.habits.ui.reminder.ReminderViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by cosmin on 1/26/18.
 *
 */
class HabitsListItemVM(rem: Reminder?, private var parentVM: HabitsListViewModel): ItemViewModel<Reminder>(rem) {

    @Inject
    lateinit var model: ReminderModel

    fun deleteClicked(){
//        if ( ::model.isInitialized ){
//            this.item?.let {
//                model.removeReminder(it)
//            }
//        }
        this.item?.let {
            parentVM.removeReminder(it)
        }
    }
}