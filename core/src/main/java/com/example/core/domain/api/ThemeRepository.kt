package com.example.core.domain.api

import com.example.core.data.model.ThemeWrapper
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    suspend fun saveTheme(isDark:Boolean)
    fun getTheme() : Flow<Boolean>
}