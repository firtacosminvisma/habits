package com.economic.habits.ui.reminder

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.economic.habits.R
import com.economic.habits.base.BaseFragment
import com.economic.habits.databinding.FragmentReminderBinding
import com.economic.habits.ui.notifications.NotificationService
import com.economic.habits.utils.AutoClearedValue
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.Context.ALARM_SERVICE
import java.util.*


/**
 * Created by cosmin on 1/26/18.
 *
 */
class ReminderFragment : BaseFragment() {
    lateinit var vm: ReminderViewModel
    lateinit var binding: AutoClearedValue<FragmentReminderBinding>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil
                .inflate<FragmentReminderBinding>(inflater, R.layout.fragment_reminder, container, false)
        binding = AutoClearedValue(this, dataBinding)

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { act ->
            vm = ViewModelProviders.of(act, viewModelFactory).get(ReminderViewModel::class.java)
            vm.state.observe(this, Observer<String> { res ->
                if (res == ReminderViewModel.UPDATE_UI) {
                    binding.get()?.let {
                        it.view = vm.view
                        it.listener = vm
                    }
                } else if (res == ReminderViewModel.CLOSE_ACT) {
                    saveReminder()
                    closeAct()
                }
            })
        }
    }

    private fun saveReminder() {

        context?.let {ctx ->
            activity?.startService(NotificationService.setAlarmIntent(ctx))
        }

    }

    private fun closeAct() {
        activity?.finish()
    }
}
