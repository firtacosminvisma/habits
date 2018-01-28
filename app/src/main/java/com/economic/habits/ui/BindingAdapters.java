package com.economic.habits.ui;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.widget.TimePicker;

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

    @BindingAdapter(value = "timeValueAttrChanged")
    public static void setListener(TimePicker timePicker, final InverseBindingListener listener){
        if ( listener != null ){
            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    listener.onChange();
                }
            });
        }
    }

    @BindingAdapter(value = "timeValue")
    public static void setTimeValue(TimePicker timePicker, int value){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ( value > 0 ) {
                timePicker.setHour(value / 60);
                timePicker.setMinute(value % 60);
            }
        }
    }

    @InverseBindingAdapter(attribute = "timeValue")
    public static int getTimeValue(TimePicker timePicker){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return timePicker.getHour()*60+timePicker.getMinute();
        }else{
            return 0;
        }
    }

}
