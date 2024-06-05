package com.example.dashboard.network

import android.content.Context
import androidx.core.content.edit

object SaveManager {
    val PREFS_NAME: String = "save_manager"
    fun save(context: Context,key: String, value: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            putString(key, value)
            apply()
        }
    }

    fun get(context: Context,key: String): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(key, null)
    }

    fun clear(context: Context,key: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            remove(key)
            apply()
        }
    }
}