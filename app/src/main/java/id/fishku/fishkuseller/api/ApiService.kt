package id.fishku.fishkuseller.api

import retrofit2.Call
import retrofit2.http.*

interface ApiService {


    @FormUrlEncoded
    @POST("register")
    fun postRegister(
        @Field("email") email: String,
        @Field("password") password: String
    )

    @FormUrlEncoded
    @POST("login")
    fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>


}