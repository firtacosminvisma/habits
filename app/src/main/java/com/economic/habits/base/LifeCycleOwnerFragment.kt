package com.economic.habits.base

import android.support.v4.app.Fragment
import com.economic.habits.di.Injectable

/**
 * a [Fragment] that overwrites the [Fragment] and [Injectable] interfaces
 * and implements the [getLifecycle] method
 */
abstract class LifeCycleOwnerFragment : Fragment() , Injectable
{
}