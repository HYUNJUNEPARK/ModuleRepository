package com.dev.modulesimulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.dev.modulesimulator.databinding.ActivityMainBinding
import com.manager.pref.SharedPreferencesManager
import com.manger.database.model.Item
import com.manger.database.viewmodel.ItemViewModel
import java.text.SimpleDateFormat
import java.util.*

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

        //sharedPreferencesManager = SharedPreferencesManager.getInstance(applicationContext)

        itemViewModel = ItemViewModel(application)
        itemViewModel.itemList.observe(this) { itemList ->
            Log.d(TAG, "itemList.observe: $itemList")
        }
    }

    fun test1() {
        Log.d(TAG, "test1")
        val item = Item(id = System.currentTimeMillis(), content = "test1")
        itemViewModel.add(item)
    }

    fun test2() {
        Log.d(TAG, "test2")
        itemViewModel.deleteAllItems()
    }

    fun test3() {
        Log.d(TAG, "test3")
    }

}