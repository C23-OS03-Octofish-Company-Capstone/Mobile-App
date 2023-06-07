package id.fishku.fishkuseller.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

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
        @Path("sellerId") sellerId: Long
    ): Call<ProfileResponse>

    @GET("seller/pesanan/{idSeller}")
    fun getAllOrders(
        @Path("idSeller") idSeller: Long
    ): Call<OrderResponse>

    @GET("seller/ikan/{sellerId}")
    fun getInventory(
        @Path("sellerId") sellerId: Long
    ): Call<InventoryResponse>

    @FormUrlEncoded
    @POST("seller/ikan/input")
    fun postFish(
        @Field("id_seller") sellerId: Long,
        @Field("name") name: String,
        @Field("weight") weight: Long,
        @Field("description") description: String,
        @Field("price") price: Long,
        @Field("photo_url") photoUrl: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @PUT("seller/ikan/edit/{idFish}")
    fun postEditFish(
        @Path("idFish") idFish: Long,
        @Field("name") name: String,
        @Field("weight") weight: Long,
        @Field("description") description: String,
        @Field("price") price: Long,
    ): Call<RegisterResponse>

    @DELETE("seller/ikan/delete/{idFish}")
    fun deleteFish(
        @Path("idFish") idFish: Long,
    ): Call<RegisterResponse>

    @GET("seller/ikan/{sellerId}/{name}")
    fun searchInventory(
        @Path("sellerId") sellerId: Long,
        @Path("name") name: String,
    ): Call<InventoryResponse>

    @Multipart
    @POST("predict")
    fun postFishEye(
        @Part file: MultipartBody.Part
    ): Call<DetectionResponse>

    @Multipart
    @POST("predict")
    fun postFishGills(
        @Part file: MultipartBody.Part
    ): Call<DetectionResponse>
}