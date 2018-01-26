package com.economic.habits.ui.reminder

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.economic.habits.R
import com.economic.habits.base.BaseActivity
import com.economic.habits.databinding.ActivityMainBinding
import com.economic.habits.ui.list.HabitsListFragment

/**
 * Created by cosmin on 1/26/18.
 *
 */
class ReminderActivity: BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            val int = Intent(context, ReminderActivity::class.java )
            int.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            return int
        }
    }

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
                .add(R.id.contentFrame, ReminderFragment())
                .commit()
    }
}