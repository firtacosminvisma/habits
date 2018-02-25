package com.economic.habits.ui.notifications

/**
 * Created by cosmin on 2/23/18.
 * Interface for the notification presenter
 */
interface INotificationView {

    fun printNotification(message: String, notificationId: Int)
    fun setAlarm(alarmTime: Long)
    fun deleteAlarm()

}