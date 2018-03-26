package com.economic.habits

import android.app.Activity
import android.app.Application
import com.economic.habits.di.AppInjector
import com.economic.habits.ui.notifications.NotificationService
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.danlew.android.joda.JodaTimeAndroid
import javax.inject.Inject

/**
 * Created by cosmin on 1/26/18.
 * The main application object of the project
 */
class MainApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this);
        AppInjector.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        AppInjector.clean()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    fun injectIntoService(service: NotificationService){
        AppInjector.inject(service)
    }

}