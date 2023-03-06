package com.manger.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.manger.database.data.ItemDatabase
import com.manger.database.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private var itemDatabase: ItemDatabase? = null

    private val _itemList = MutableLiveData<List<Item>>()
    val itemList: LiveData<List<Item>>
        get() = _itemList

    init {
        if (itemDatabase == null) {
            itemDatabase = ItemDatabase.getInstance(context)
        }
    }

    /**
     * DB 에 아이템을 저장한다.
     *
     * @param item 저장하려는 아이템
     */
    fun add(item: Item) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                itemDatabase!!.itemDao().add(item)

                //데이터 동기화
                getAllItems()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * DB 에 저장된 모든 데이터를 가져와, LiveData(memoList) 를 초기화한다.
     */
    fun getAllItems() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                itemDatabase!!.itemDao().getAllItems().let { itemList ->
                    withContext(Dispatchers.Main) {
                        _itemList.value = itemList
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * DB 에 저장된 특정 아이템을 수정한다.
     *
     * @param newItem 수정하려는 아이템
     */
    fun update(newItem: Item) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                itemDatabase!!.itemDao().update(newItem)

                //데이터 동기화
                getAllItems()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * DB 에 저장된 특정 아이템을 삭제한다.
     *
     * @param item 삭제하려는 아이템
     */
    fun delete(item: Item) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                itemDatabase!!.itemDao().delete(item)

                //데이터 동기화
                getAllItems()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * DB 에 저장된 모든 아이템을 삭제한다.
     */
    fun deleteAllItems() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                itemDatabase!!.itemDao().deleteAllItems()

                //데이터 동기화
                getAllItems()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}