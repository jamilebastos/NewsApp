package dev.jamile.newsapp.network

import dev.jamile.newsapp.BuildConfig.API_KEY
import dev.jamile.newsapp.network.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun list(
        @Query("country") country: String,
        @Query("api_key") apiKey: String = API_KEY
    ): NewsResponse

}