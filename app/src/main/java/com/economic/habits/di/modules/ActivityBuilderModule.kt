package com.economic.habits.di.modules

import com.economic.habits.ui.list.HabitsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by cosmin on 1/26/18.
 * Will contribute with injecting into the activities
 */

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    internal abstract fun contributeHabitsListActivity(): HabitsListActivity
}