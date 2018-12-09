package com.nandaadisaputra.medicinal_plants_marketplace.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object InitRetrofit {
    val BASE_URL = "http://192.168.43.216/user/"
    val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .retryOnConnectionFailure(true)
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    val gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getInstance(): RestApi = retrofit.create(RestApi::class.java)

}