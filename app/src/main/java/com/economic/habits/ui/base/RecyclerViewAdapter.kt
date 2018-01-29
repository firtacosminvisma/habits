package com.economic.habits.ui.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View


/**
 * Created by cosmin on 12/13/17.
 *
 */
abstract class RecyclerViewAdapter<ITEM_T, VIEW_MODEL_T : ItemViewModel<ITEM_T>> : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder<ITEM_T, VIEW_MODEL_T>>() {
    var items: ArrayList<ITEM_T> = ArrayList()
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onBindViewHolder(holder: ItemViewHolder<ITEM_T, VIEW_MODEL_T>, position: Int) {
        Log.d("RecyclerViewAdapter","::onBindViewHolder"+items[position])
        holder.setItem(items[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder<T, out VT : ItemViewModel<T>>(itemView: View, val binding: ViewDataBinding, val viewModel: VT) : RecyclerView.ViewHolder(itemView) {
        internal fun setItem(item: T) {
            viewModel.item = item
            binding.executePendingBindings()
            var a :Int
        }
    }

    protected abstract fun createLayoutManager(): RecyclerView.LayoutManager

    fun setupRecyclerView(recyclerView: RecyclerView) {
        layoutManager = createLayoutManager()
        recyclerView.adapter = this
        recyclerView.layoutManager = layoutManager
    }
}