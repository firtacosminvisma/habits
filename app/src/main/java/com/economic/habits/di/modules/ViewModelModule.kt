package com.economic.habits.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.economic.habits.di.ViewModelFactory
import com.economic.habits.di.ViewModelKey
import com.economic.habits.ui.list.HabitsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by cosmin on 1/26/18.
 */

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HabitsListViewModel::class)
    internal abstract fun bindHabitsListViewModel(viewModel: HabitsListViewModel): ViewModel
}