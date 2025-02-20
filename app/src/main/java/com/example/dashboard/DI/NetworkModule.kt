package com.example.dashboard.DI

import android.content.Context
import com.example.dashboard.common.IP_HOST
import com.example.dashboard.data.NetworkConnectionInterceptor
import com.example.dashboard.data.datasource.AuthService
import com.example.dashboard.data.datasource.DashDataService
import com.example.dashboard.data.datasource.SettingsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {



    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context)) // Добавляем перехватчик
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(IP_HOST)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}