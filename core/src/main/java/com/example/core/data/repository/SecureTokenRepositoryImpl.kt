package com.example.core.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.core.common.MODULE_TAG
import com.example.core.domain.api.SecureTokenRepository
import com.example.core.utils.TOKEN_SAVE_NAME
import javax.inject.Inject
import javax.inject.Singleton


private val TAG = "SecureTokenRepositoryImpl"
@Singleton
class SecureTokenRepositoryImpl @Inject constructor(
    private val context: Context
): SecureTokenRepository {


    init{
        Log.d(TAG,context.hashCode().toString())
    }
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
        Log.d("$MODULE_TAG/$TAG","save " + context.hashCode().toString() +" " + token)
        sharedPreferences.edit().putString(TOKEN_NAME,token).apply()
    }

    override fun getToken(): String? {
        Log.d("$MODULE_TAG/$TAG","get " + context.hashCode().toString() +" " + sharedPreferences.getString(TOKEN_NAME,null).toString())
        return sharedPreferences.getString(TOKEN_NAME,null)
    }

    override fun clearToken() {
        Log.d("$MODULE_TAG/$TAG","clearToken " + context.hashCode().toString())
        sharedPreferences.edit().remove(TOKEN_NAME).apply()
    }

    companion object{
        private const val PREFS_NAME = "secure_pref"
        private const val TOKEN_NAME = TOKEN_SAVE_NAME
    }
}