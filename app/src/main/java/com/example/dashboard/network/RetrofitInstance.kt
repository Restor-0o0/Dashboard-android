package com.example.dashboard.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthService { //Интерфейс для авторизации
    @FormUrlEncoded
    @POST(AUTH_PATH)
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): User
}

interface DashDataService{ //Интерфейс для обмена данными
    @GET(DATA_LIST)
    suspend fun getData( //Получение данных
        @Header("Authorization") token: String?
    ): Dashdata
    @GET(TYPES_LIST)
    suspend fun getTypesCount( //Получение типов группировки
        @Header("Authorization") token: String?
    ): MutableList<TypesCount>
    @GET(DRAW_LIST)
    suspend fun getDrawingTypes( // Получение типов отрисовки
        @Header("Authorization") token: String?
    ): MutableList<DrawingTypes>
    @GET(SETT_LIST)
    suspend fun getSettingsData( // Получение данных для настройки
        @Header("Authorization") token: String?
    ): MutableList<SettingsData>
    @PUT(SETT_LIST)
    suspend fun setSettingsData( // Отправка данных для отрисовки
        @Header("Authorization") token: String?,
        @Body data: MutableList<SettingsData>
    )
}
object RetrofitInstance { //Объект реализующий интерфейс
    private const val BASE_URL = IP_HOST

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