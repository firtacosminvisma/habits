package com.economic.habits.ui.base

import android.arch.lifecycle.ViewModel



/**
 * Created by cosmin on 12/13/17.
 *
 */
abstract class ItemViewModel<ITEM_T> (
        var item:ITEM_T?
) : ViewModel() {
}