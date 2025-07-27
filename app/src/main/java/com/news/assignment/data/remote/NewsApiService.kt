package com.news.assignment.data.remote

import com.news.assignment.data.model.NewsFeedModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface NewsApiService {
    @GET
    suspend fun getNewsFeed(
        @Url url: String,
        @Query("country") country: String? = "us",
        @Query("pageSize") pageSize: String?,
        @Query("page") page: String?,
        @Query("apiKey") apiKey: String
    ): Response<NewsFeedModel>
}
