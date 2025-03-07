package com.example.dashboard.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.dashboard.domain.api.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val context: Context
) : ThemeRepository {


    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="dash_store")


    companion object{
       private val THEME_KEY = booleanPreferencesKey("theme_dash")
   }

    override suspend fun saveTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDark
        }
    }

    override fun getTheme(): Flow<Boolean> {
        return context.dataStore.data.map { preferences -> preferences[THEME_KEY] ?: false }
    }


}