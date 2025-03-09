package com.example.core.domain.api

import kotlinx.coroutines.flow.Flow

interface LocalDataStore {
    suspend fun save(isDark: Boolean)
    fun get(): Flow<Boolean>
}