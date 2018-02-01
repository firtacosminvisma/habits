package com.economic.habits.ui.list.list

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.economic.habits.R
import com.economic.habits.data.Reminder
import com.economic.habits.databinding.HabitsListItemBinding
import com.economic.habits.ui.base.RecyclerViewAdapter
import com.economic.habits.ui.list.HabitsListViewModel

/**
 * Created by cosmin on 1/26/18.
 *
 */
class HabitsListAdapter(
        private val context: Context,
        private val parentVM: HabitsListViewModel
) : RecyclerViewAdapter<Reminder, HabitsListItemVM>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder<Reminder, HabitsListItemVM> {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = DataBindingUtil.inflate<HabitsListItemBinding>(inflater!!, R.layout.habits_list_item, parent, false)
        val vm = HabitsListItemVM(null, parentVM)
        val holder = ItemViewHolder(binding.root, binding, vm)
        binding.vm = vm
        binding.reminder = vm.item
        return holder
    }
    override fun onBindViewHolder(holder: ItemViewHolder<Reminder, HabitsListItemVM>, position: Int) {
        Log.d("HabitsListAdapter", "::onBindViewHolder"+items[position])
        holder.setItem(items[position])
        (holder.binding as? HabitsListItemBinding)?.reminder = holder.viewModel.item
        holder.binding.executePendingBindings()
    }


    override fun createLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }
}