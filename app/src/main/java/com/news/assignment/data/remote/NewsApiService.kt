package com.news.assignment.data.remote

import com.news.assignment.data.model.NewsFeedModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {
    @GET("top-headlines")
    suspend fun getNewsFeed(
        @Query("country") country: String? = "us",
        @Query("pageSize") pageSize: String?,
        @Query("page") page: String?,
        @Query("apiKey") apiKey: String
    ): Response<NewsFeedModel>
}
