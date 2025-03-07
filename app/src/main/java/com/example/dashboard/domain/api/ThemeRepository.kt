package com.example.dashboard.domain.api

import com.example.dashboard.data.model.ThemeWrapper
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    suspend fun saveTheme(isDark:Boolean)
    fun getTheme() : Flow<Boolean>
}