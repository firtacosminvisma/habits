package com.economic.habits.ui.notifications

import com.economic.habits.ui.model.ReminderModel
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import java.lang.ref.WeakReference
import javax.inject.Inject
import java.util.TimeZone


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
        val tz = DateTimeZone.getDefault()
        val nowUTC = DateTime(DateTimeZone.UTC)
        val nowLocal = nowUTC.withZone(tz)
        setAlarmAfterMin(nowLocal.minuteOfDay)
    }

    fun displayAlarm() {

        val tz = DateTimeZone.getDefault()
        val nowUTC = DateTime(DateTimeZone.UTC)
        val nowLocal = nowUTC.withZone(tz)
        val reminders = model.getAllWithMin(nowLocal.minuteOfDay)
        reminders.forEach {
            notificationView.get()?.printNotification(it.message, it.uid)
        }
        setAlarmAfterMin(DateTime.now().minuteOfDay+1)
    }

    fun notificationTap() {

    }


    private fun minuteToMilli(minute: Int): Long{
        val tz = DateTimeZone.getDefault()
        val nowUTC = DateTime(DateTimeZone.UTC)
        val now = nowUTC.withZone(tz)
//        val midNight = DateTime(now.year().get(), now.monthOfYear().get(), now.dayOfMonth, 0, 0, 0).toLocalDateTime()
        val tz1 = TimeZone.getDefault()
        System.out.println(
            "TimeZone   " + tz1.getDisplayName(
                false,
                TimeZone.SHORT
            ) + " Timezon id :: " + tz1.id
        )
        val dtz = DateTimeZone.forTimeZone(tz1)
        val midNight = DateTime(dtz).withTimeAtStartOfDay()
        return midNight.plusMinutes(minute).toDateTime(tz).millis
    }

    private fun setAlarmAfterMin(min: Int){
        val reminder = model.getFirstAfter(min)
        reminder?.let {
            notificationView.get()?.deleteAlarm()
            notificationView.get()?.setAlarm(minuteToMilli(it.minute))
        }
    }
}