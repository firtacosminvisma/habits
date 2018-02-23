package com.economic.habits.ui.notifications

import android.app.AlarmManager
import android.app.IntentService
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.economic.habits.R
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import java.util.*
import javax.inject.Inject


/**
 * Created by cosmin on 1/31/18.
 * The service that will be notified by the alarm and print a notification
 */
class NotificationService : IntentService("NotificationService"), INotificationView {

    /**
     *
     *  - set alarm intent
     *  - display notif intent -> from the alarm manager
     *  -
     *
     *
     */

    @Inject lateinit var presenter: NotificationPresenter

    companion object {

        val SET_ALARM_ACTION = "com.economic.habits.ui.notifications.NotificationService.SET_ALARM"
        val DISPLAY_ALARM_ACTION = "com.economic.habits.ui.notifications.NotificationService.DISPLAY_ALARM"


        fun setAlarmIntent(c: Context): Intent {
            val intent = Intent(c, NotificationService.javaClass)
            intent.action = SET_ALARM_ACTION
            return intent
        }

        fun displayAlarmIntent(c: Context): Intent{
            val intent = Intent(c, NotificationService.javaClass)
            intent.action = DISPLAY_ALARM_ACTION
            return intent
        }
    }

    override fun onHandleIntent( intent: Intent ) {
        presenter.view = this
        when(intent.action) {
           SET_ALARM_ACTION -> presenter.setAlarm(intent)
           DISPLAY_ALARM_ACTION -> presenter.displayAlarm(intent)
        }
    }

    override fun printNotification(message: String) {
        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My notification")
                .setContentText(message)
        // Sets an ID for the notification
        val mNotificationId = 1
        // Gets an instance of the NotificationManager service
        val mNotifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build())
    }

    override fun setAlarm(alarmTime: Long) {
        val  alarmMgr: AlarmManager = baseContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val  alarmIntent: PendingIntent
        val intent = NotificationService.displayAlarmIntent(baseContext)
        alarmIntent = PendingIntent.getBroadcast(baseContext, 0, intent, 0)

        val calendar: Calendar = Calendar.getInstance();
        calendar.timeInMillis = alarmTime;

        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmIntent)
    }

}
