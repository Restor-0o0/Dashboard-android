package com.example.core.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.core.domain.api.SecureTokenRepository
import com.example.core.utils.TOKEN_SAVE_NAME
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecureTokenRepositoryImpl @Inject constructor(
    context: Context
): SecureTokenRepository {

    private val masterKey: String =  MasterKeys.getOrCreate(
        MasterKeys.AES256_GCM_SPEC
    )

    private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        PREFS_NAME,
        masterKey,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_NAME,token).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_NAME,null)
    }

    override fun clearToken() {
        sharedPreferences.edit().remove(TOKEN_NAME).apply()
    }

    companion object{
        private const val PREFS_NAME = "secure_pref"
        private const val TOKEN_NAME = TOKEN_SAVE_NAME
    }
}