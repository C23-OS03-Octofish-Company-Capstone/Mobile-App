package id.fishku.fishkuseller.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>

}