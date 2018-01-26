package com.economic.habits.utils

import android.util.Log

/**
 * A class that will be injected in all the classes that want to log.
 * It is used because it is easier to mock for unit tests
 */
class Log {

    fun d(TAG:String, message:String){
        Log.d(TAG,message)
    }
}