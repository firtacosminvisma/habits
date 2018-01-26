package com.economic.habits.di.modules

import com.economic.habits.data.AppDatabase
import com.economic.habits.di.AppScope
import dagger.Module
import dagger.Provides
import android.arch.persistence.room.Room
import android.content.Context
import com.economic.habits.data.ReminderDao


/**
 * Created by cosmin on 1/26/18.
 * The [DaggerModule]
 */
@Module
class DbModule {

    @Provides
    @AppScope
    fun provideDb(c:Context): AppDatabase{
        return Room.databaseBuilder(c.applicationContext,
                                    AppDatabase::class.java, "habitsDb").build()
    }

    @Provides
    @AppScope
    fun provideReminderDAO(db: AppDatabase): ReminderDao{
        return db.reminderDao()
    }
}