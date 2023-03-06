package com.manager.pref

import android.content.Context
import android.content.SharedPreferences
import com.manager.pref.PrefConst.PREF_FILE_NAME

/**
 * XML 파일 위치 : data > data > 패키지명 > shared_prefs > pref.xml
 */
class SharedPreferencesManager private constructor(context: Context) {
    companion object {
        private var instance: SharedPreferencesManager? = null

        fun getInstance(_context: Context): SharedPreferencesManager {
            return instance ?: synchronized(this) {
                instance ?: SharedPreferencesManager(_context).also {
                    instance = it
                }
            }
        }
    }

    private val prefs: SharedPreferences
    private val prefsEditor: SharedPreferences.Editor

    init {
        prefs = context.getSharedPreferences(
            PREF_FILE_NAME,
            Context.MODE_PRIVATE
        )
        prefsEditor = prefs.edit()
    }

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue) ?: defValue
    }

    fun getInt(key: String, defValue: Int): Int {
        return prefs.getInt(key, defValue)
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return prefs.getBoolean(key, defValue)
    }

    /**
     * pref 에 저장된 모든 key 를 리스트 형태로 가져온다.
     */
    fun getKeyLists(): List<String> {
        val keys: Map<String, *> = prefs.all
        val keyList: MutableList<String> = mutableListOf()

        for ((key, _) in keys.entries) {
            keyList.add(key)
        }

        return keyList
    }

    /**
     * pref 에 저장된 모든 key=value 를 가져온다.
     */
    fun getAllLists(): HashMap<String, Any> {
        val keys: Map<String, *> = prefs.all
        val hashMap = HashMap<String, Any>()

        for ((key, value) in keys.entries) {
            hashMap[key] = value!!
        }

        return hashMap
    }

    fun putString(key: String, value: String) {
        prefsEditor.apply {
            putString(key, value)
            apply()
        }
    }

    fun putInt(key: String, value: Int) {
        prefsEditor.apply {
            putInt(key, value)
            apply()
        }
    }

    fun putBoolean(key: String, value: Boolean) {
        prefsEditor.apply {
            putBoolean(key, value)
            apply()
        }
    }

    /**
     * Key(param) : Value 를 지운다.
     */
    fun remove(key: String) {
        prefsEditor.apply {
            remove(key)
            apply()
        }
    }

    /**
     * pref 에 있는 모든 데이터를 지운다.
     */
    fun removeAll() {
        prefsEditor.apply {
            clear()
            apply()
        }
    }
}