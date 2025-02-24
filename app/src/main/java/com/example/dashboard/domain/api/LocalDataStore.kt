package com.example.dashboard.domain.api

import kotlinx.coroutines.flow.Flow

interface LocalDataStore {
    suspend fun save(isDark: Boolean)
    fun get(): Flow<Boolean>
}