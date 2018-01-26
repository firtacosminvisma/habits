package com.economic.habits.di

import android.app.Application
import com.economic.habits.MainApp
import com.economic.habits.di.modules.ActivityBuilderModule
import com.economic.habits.di.modules.AppModule
import com.economic.habits.di.modules.DbModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

/**
 * Created by cosmin on 1/26/18.
 * The [dagger.Component] component for the main app [MainApp]
 */
@AppScope
@Component(modules =
           [
               (AndroidInjectionModule::class),
               (AppModule::class),
               (ActivityBuilderModule::class),
               (DbModule::class)
           ])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainApp: MainApp)
}