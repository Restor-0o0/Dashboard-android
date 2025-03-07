package com.example.dashboard.domain.UseCase

import com.example.dashboard.domain.api.ThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeUseCase@Inject constructor(
    private val themeRepository:  ThemeRepository
) {
    suspend fun saveTheme(isDark: Boolean){
        themeRepository.saveTheme(isDark)
    }
    fun getTheme() : Flow<Boolean> {
        return themeRepository.getTheme()
    }
}