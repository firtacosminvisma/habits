package com.economic.habits.ui.notifications

import android.app.AlarmManager
import android.app.IntentService
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.economic.habits.R
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import javax.inject.Inject
import android.support.v4.app.NotificationManagerCompat
import android.app.NotificationChannel
import android.os.Build
import android.support.annotation.RequiresApi
import com.economic.habits.MainApp


/**
 * Created by cosmin on 1/31/18.
 * The service that will be notified by the alarm and print a notification
 */
class NotificationService : IntentService("NotificationService"), INotificationView {

    /**
     *
     *  - set alarm intent
     *  - display notif intent -> from the alarm manager
     *  - notification taped
     *
     *
     */

    @Inject lateinit var presenter: NotificationPresenter

    companion object {

        val SET_ALARM_ACTION = "com.economic.habits.ui.notifications.NotificationService.SET_ALARM"
        val DISPLAY_ALARM_ACTION = "com.economic.habits.ui.notifications.NotificationService.DISPLAY_ALARM"
        val NOTIFICATION_TAP_ACTION = "com.economic.habits.ui.notifications.NotificationService.NOTIFICATION_TAP"

        val NOTIFICATION_CHANNEL_ID = "com.economic.habits.ui.notifications.NotificationService"


        fun setAlarmIntent(c: Context): Intent {
            val intent = Intent(c, NotificationService::class.java)
            intent.action = SET_ALARM_ACTION
            return intent
        }

        fun displayAlarmIntent(c: Context): Intent{
            val intent = Intent(c, NotificationService::class.java)
            intent.action = DISPLAY_ALARM_ACTION
            return intent
        }
        fun getNotificationTapedIntent(c: Context): Intent{
            val intent = Intent(c, NotificationService::class.java)
            intent.action = NOTIFICATION_TAP_ACTION
            return intent
        }

    }

    override fun onHandleIntent( intent: Intent ) {
        injectStuff()
        presenter?.let{
            presenter.view = this
            when(intent.action) {
                SET_ALARM_ACTION -> presenter.setAlarm()
                DISPLAY_ALARM_ACTION -> presenter.displayAlarm()
                NOTIFICATION_TAP_ACTION -> presenter.notificationTap()
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun printNotification(message: String, notificationId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            val name = getString(R.string.channel_name)
            val description = getString(R.string.channel_description)
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = description
            // Register the channel with the system
//            val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(this)
            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val tapIntent = PendingIntent.getService(applicationContext, 0, NotificationService.getNotificationTapedIntent(baseContext), 0)
        val mBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_done_black_24dp)
                .setContentTitle("Habits")
                .setContentText(message)
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText(message))
                .setContentIntent(tapIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
        val notificationManager = NotificationManagerCompat.from(this)
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, mBuilder.build())

    }

    override fun setAlarm(alarmTime: Long) {
        val  alarmMgr: AlarmManager = baseContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = createAlarmPendingIntent()

        alarmMgr.set(AlarmManager.RTC_WAKEUP, alarmTime, alarmIntent)
    }

    override fun deleteAlarm() {
        val  alarmMgr: AlarmManager = baseContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = createAlarmPendingIntent()
        alarmMgr.cancel(alarmIntent)
    }

    private fun createAlarmPendingIntent(): PendingIntent{
        val  alarmIntent: PendingIntent
        val intent = NotificationService.displayAlarmIntent(baseContext)
        alarmIntent = PendingIntent.getService(baseContext, 0, intent, 0)
        return alarmIntent
    }

    private fun injectStuff() {
        (application as? MainApp)?.injectIntoService(this)
    }
}
