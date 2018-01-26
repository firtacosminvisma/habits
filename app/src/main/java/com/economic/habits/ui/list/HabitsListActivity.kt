package com.economic.habits.ui.list

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import com.economic.habits.R
import com.economic.habits.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class HabitsListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
