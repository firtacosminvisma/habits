package com.economic.habits.base

import android.arch.lifecycle.ViewModelProvider
import com.economic.habits.di.Injectable
import com.economic.habits.utils.AutoClearedValue
import javax.inject.Inject


/**
 * Created by cosmin on 11/22/17.
 * A base fragment class that will be extended by all the fragments within the app
 */
open class BaseFragment: LifeCycleOwnerFragment(), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * method called when the back button is pressed.
     * If it returns true then the back press is consumed by the fragment and will be ignored by
     * the activity
     */
    open fun onBackPressed():Boolean{
        return false
    }
}