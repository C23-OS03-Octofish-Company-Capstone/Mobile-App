package id.fishku.fishkuseller.api

import retrofit2.Call
import retrofit2.http.*

interface ApiService {


    @FormUrlEncoded
    @POST("seller/regist")
    fun postRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phone_number") phoneNumber: Int,
        @Field("location") location: String,
        @Field("roles") roles: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("seller/login")
    fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>


}