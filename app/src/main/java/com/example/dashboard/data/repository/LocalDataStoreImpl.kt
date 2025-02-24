package com.example.dashboard.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.dashboard.domain.api.LocalDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataStoreImpl @Inject constructor(
    private val context: Context
) : LocalDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="dash_store")

    companion object{
        private val DARK_MODE_KEY = booleanPreferencesKey("dark")
    }

    override suspend fun save(isDark:Boolean){
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = isDark
        }
    }
    override fun get(): Flow<Boolean> {
        return context.dataStore.data.map { preferences -> preferences[DARK_MODE_KEY] ?: false }
    }
}