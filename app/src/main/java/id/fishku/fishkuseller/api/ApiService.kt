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
        @Field("phone_number") phoneNumber: Long,
        @Field("location") location: String,
        @Field("roles") roles: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("seller/login")
    fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("seller/profile/{sellerId}")
    fun getProfile(
        @Path("sellerId") sellerId: Int
    ): Call<ProfileResponse>

    @GET("seller/pesanan/{idSeller}")
    fun getAllOrders(
        @Path("idSeller") idSeller: Long
    ): Call<OrderResponse>
}