package com.economic.habits.di.modules

import com.economic.habits.ui.list.HabitsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by cosmin on 1/26/18.
 * Will contribute to injecting into the fragments
 */
@Module
abstract class FragmentBuildersModule {

        @ContributesAndroidInjector
        internal abstract fun contributeHabitListFragment(): HabitsListFragment
}