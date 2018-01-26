package com.economic.habits.di.modules

import android.arch.lifecycle.ViewModelProvider
import com.economic.habits.di.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by cosmin on 1/26/18.
 */

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}