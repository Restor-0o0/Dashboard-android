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

interface AuthService {
    @FormUrlEncoded
    @POST(AUTH_PATH)
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): User
}

interface DashDataService{
    @GET(DATA_LIST)
    suspend fun getData(
        @Header("Authorization") token: String?
    ): Dashdata
    @GET(TYPES_LIST)
    suspend fun getTypesCount(
        @Header("Authorization") token: String?
    ): MutableList<TypesCount>
    @GET(DRAW_LIST)
    suspend fun getDrawingTypes(
        @Header("Authorization") token: String?
    ): MutableList<DrawingTypes>
    @GET(SETT_LIST)
    suspend fun getSettingsData(
        @Header("Authorization") token: String?
    ): MutableList<SettingsData>
    @PUT(SETT_LIST)
    suspend fun setSettingsData(
        @Header("Authorization") token: String?,
        @Body data: MutableList<SettingsData>
    )
}

object RetrofitInstance {
    private const val BASE_URL = IP_HOST

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
    val dataDashService: DashDataService by lazy{
        retrofit.create(DashDataService::class.java)
    }


}