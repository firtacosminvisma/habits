package com.economic.habits.ui.list

import com.economic.habits.data.Reminder

/**
 * Created by cosmin on 1/26/18.
 *
 */
class HabitsListView {
    var reminders:MutableList<Reminder> = ArrayList()
    var loading = true
    var time:String = ""
    var minutes:Int = 0
    set(value) {
        field = value
        time = "${value/60}:${value/60}"
    }
}