package com.economic.habits.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.economic.habits.R
import com.economic.habits.base.BaseFragment

/**
 * A placeholder fragment containing a simple view.
 */
class HabitsListFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}
