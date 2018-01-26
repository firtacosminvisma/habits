package com.economic.habits.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.economic.habits.R
import com.economic.habits.base.BaseFragment
import com.economic.habits.databinding.FragmentMainBinding
import com.economic.habits.ui.list.list.HabitsListAdapter
import com.economic.habits.ui.reminder.ReminderActivity
import com.economic.habits.utils.AutoClearedValue

/**
 * A placeholder fragment containing a simple view.
 */
class HabitsListFragment : BaseFragment() {

    lateinit var vm: HabitsListViewModel
    lateinit var binding: AutoClearedValue<FragmentMainBinding>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil
                .inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)
        binding = AutoClearedValue(this, dataBinding)

        return dataBinding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm = ViewModelProviders.of(activity, viewModelFactory).get(HabitsListViewModel::class.java)
        vm.state.observe(this, Observer<String> { action ->
            if ( action == HabitsListViewModel.UPDATE_UI ) {
                vm.view.reminders.let { reminders ->
                    binding.get()?.let {
                        if (it.habitsList.adapter != null) {
                            (it.habitsList.adapter as? HabitsListAdapter)?.let {
                                it.items.clear()
                                it.items.addAll(reminders.asIterable())
                                it.notifyDataSetChanged()
                            }
                        } else {
                            val adapter = HabitsListAdapter(context, vm)
                            adapter.items.addAll(reminders.asIterable())
                            it.adapter = adapter
                        }
                        it.view = vm.view
                        it.listener = vm
                        it.executePendingBindings()
                    }
                }
            }else if ( action == HabitsListViewModel.GO_TO_NEW_REMINDER ){
                goToAddReminder()
            }
        })
    }

    private fun goToAddReminder() {
        startActivity(ReminderActivity.getIntent(context))
        activity.finish()
    }

}
