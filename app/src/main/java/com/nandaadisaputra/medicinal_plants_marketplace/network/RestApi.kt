package com.nandaadisaputra.medicinal_plants_marketplace.network

import com.nandaadisaputra.medicinal_plants_marketplace.model.ResponseRegisterLogin
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RestApi {

    @FormUrlEncoded
    @POST("register.php")
    fun registeruser(
        @Field("username") username: String,
        @Field("nomorhp") nomorhp: String,
        @Field("email") email: String,
        @Field("alamat") alamat: String,
        @Field("password") password: String,
        @Field("level") level: String
    ) : Call<ResponseRegisterLogin>

    @FormUrlEncoded
    @POST("login.php")
    fun loginuser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseRegisterLogin>


}
