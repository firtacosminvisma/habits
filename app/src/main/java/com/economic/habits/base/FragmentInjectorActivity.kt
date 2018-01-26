package com.economic.habits.base

import android.arch.lifecycle.LifecycleRegistry
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by Cosmin on 9/28/2017.
 *
 */
abstract class FragmentInjectorActivity : AppCompatActivity(), HasSupportFragmentInjector {
    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

}