package com.economic.habits.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.CallSuper

/**
 * Created by Cosmin on 9/28/2017.
 * The class that will be extended by all the activities in the app
 */
@SuppressLint("Registered")
open class BaseActivity: FragmentInjectorActivity() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}