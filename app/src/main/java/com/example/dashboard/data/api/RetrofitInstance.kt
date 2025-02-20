package com.example.dashboard.data.api

import com.example.dashboard.common.IP_HOST
import com.example.dashboard.data.datasource.AuthService
import com.example.dashboard.data.datasource.DashDataService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance { //Объект реализующий интерфейс
    const val BASE_URL = IP_HOST

    private val retrofit by lazy { //свойство реализующее ретрофит
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService: AuthService by lazy { //свойство реализующее интерфейс авторизации
        retrofit.create(AuthService::class.java)
    }
    val dataDashService: DashDataService by lazy{ //свойство реализующее интерфейс обмена данными
        retrofit.create(DashDataService::class.java)
    }
}