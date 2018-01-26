package com.economic.habits.ui;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.economic.habits.ui.base.RecyclerViewAdapter;

/**
 * Created by cosmin on 1/26/18.
 *
 */

public class BindingAdapters {

    @BindingAdapter({"bind:adapter"})
    public static void setRecyclerViewAdapter( RecyclerView recyclerView,
                                RecyclerViewAdapter adapter) {
        if ( adapter != null ) {
            adapter.setupRecyclerView(recyclerView);
        }
    }
}
