package com.economic.habits.ui.notifications

import com.economic.habits.ui.model.ReminderModel
import org.joda.time.DateTime
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by cosmin on 2/9/18.
 * The ViewModel that will be used to manage the notification service
 */
class NotificationPresenter @Inject constructor(private var model: ReminderModel) {

    var view: INotificationView?
        set(value) {
        value?.let {
            notificationView = WeakReference(value)
        }
    }
    get() = notificationView.get()

    private var notificationView: WeakReference<INotificationView?> = WeakReference(null)

    fun setAlarm() {
        /*get first 2 alarms*/
        setAlarmAfterMin(DateTime.now().minuteOfDay)
    }

    fun displayAlarm() {
        val reminders = model.getAllWithMin(DateTime.now().minuteOfDay)
        reminders.forEach {
            notificationView.get()?.printNotification(it.message, it.uid)
        }
        setAlarmAfterMin(DateTime.now().minuteOfDay+1)
    }

    fun notificationTap() {

    }


    private fun minuteToMilli(minute: Int): Long{
        val now = DateTime()
        val midNight = DateTime(now.year().get(), now.monthOfYear().get(), now.dayOfMonth, 0, 0, 0)
        return midNight.plusMinutes(minute).millis
    }

    private fun setAlarmAfterMin(min: Int){
        val reminder = model.getFirstAfter(min)
        reminder?.let {
            notificationView.get()?.deleteAlarm()
            notificationView.get()?.setAlarm(minuteToMilli(it.minute))
        }
    }
}