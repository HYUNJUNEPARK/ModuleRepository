package com.dev.modulesimulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.dev.modulesimulator.databinding.ActivityMainBinding
import com.manager.pref.SharedPreferencesManager
import com.manger.database.viewmodel.ItemViewModel

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "testLog"
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferencesManager: SharedPreferencesManager
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this@MainActivity

        //TODO SharedPreferencesManager
//        sharedPreferencesManager = SharedPreferencesManager.getInstance(applicationContext)

        //TODO DatabaseManager
//        itemViewModel = ItemViewModel(application)
//        itemViewModel.itemList.observe(this) { itemList ->
//            Log.d(TAG, "itemList.observe: $itemList")
//        }

    }

    fun test1() {
        Log.d(TAG, "test1")
    }

    fun test2() {
        Log.d(TAG, "test2")
    }

    fun test3() {
        Log.d(TAG, "test3")
    }

}