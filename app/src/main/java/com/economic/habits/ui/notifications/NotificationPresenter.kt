package com.economic.habits.ui.notifications

import android.content.Intent
import com.economic.habits.ui.model.ReminderModel
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

    private var notificationView:WeakReference<INotificationView?> = WeakReference(null)



    fun setAlarm( intent: Intent) {

    }

    fun displayAlarm( intent: Intent) {

    }
}