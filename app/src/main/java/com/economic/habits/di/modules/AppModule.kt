package com.economic.habits.di.modules

import android.app.Application
import android.content.Context
import com.economic.habits.di.AppScope
import com.economic.habits.utils.Log
import dagger.Module
import dagger.Provides

/**
 * Created by cosmin on 1/26/18.
 */

@Module(includes = [
    (ViewModelModule::class),
    (DbModule::class)
])
class AppModule {

    @AppScope
    @Provides
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @AppScope
    @Provides
    fun provideLOG(): Log = Log()
}