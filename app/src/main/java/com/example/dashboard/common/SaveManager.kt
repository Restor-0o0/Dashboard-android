package com.example.dashboard.common

import android.content.Context
import androidx.core.content.edit
import javax.inject.Inject

class SaveManager @Inject constructor(
    private val context: Context
) {
    private val PREFS_NAME: String = "save_manager"
    fun save(key: String, value: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            putString(key, value)
            apply()
        }
    }

    fun get(key: String): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(key, null)
    }

    fun clear(key: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            remove(key)
            apply()
        }
    }
}