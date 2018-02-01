package com.economic.habits.ui.notifications

import android.app.IntentService
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.economic.habits.R
import android.app.NotificationManager
import android.content.Context


/**
 * Created by cosmin on 1/31/18.
 * The service that will be notified by the alarm and print a notification
 */
class NotificationService : IntentService("NotificationService") {

    override fun onHandleIntent(intent: Intent) {
        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
        // Sets an ID for the notification
        val mNotificationId = 1
        // Gets an instance of the NotificationManager service
        val mNotifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build())
    }
}