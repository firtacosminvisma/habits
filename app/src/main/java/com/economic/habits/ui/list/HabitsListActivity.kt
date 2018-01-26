package com.economic.habits.ui.list

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.economic.habits.R
import com.economic.habits.base.BaseActivity
import com.economic.habits.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class HabitsListActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        setSupportActionBar(toolbar)


        addFragment()
    }

    private fun addFragment(){
        supportFragmentManager
                .beginTransaction()
                .add(R.id.contentFrame, HabitsListFragment())
                .commit()
    }
}
