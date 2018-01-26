package com.economic.habits.utils

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


/**
 * A value holder that automatically clears the reference if the Fragment's view is destroyed.
 *
 * @param <T>
</T> */
class AutoClearedValue<T> : LifecycleObserver {
    private var value: T? = null

    constructor(fragment: Fragment, value: T) {
        val fragmentManager = fragment.fragmentManager
        fragmentManager.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentViewDestroyed(fm: FragmentManager?, f: Fragment?) {
                        if (f === fragment) {
                            this@AutoClearedValue.value = null
                            fragmentManager.unregisterFragmentLifecycleCallbacks(this)
                        }
                    }
                }, false)
        this.value = value
    }

    constructor(owner: LifecycleOwner, value: T) {
        owner.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                this@AutoClearedValue.value = null
                owner.lifecycle.removeObserver(this)
            }
        })
        this.value = value
    }

    fun get(): T? {
        return value
    }
}
