package com.news.assignment.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.news.assignment.BuildConfig
import com.news.assignment.data.model.Article

/**
 * Paging 3 source for news api pagination
 */
class NewsPagingSource(
    private val api: NewsApiService
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            Log.d("NewsPagingSource", "load: page=$page, loadSize=${params.loadSize}")
            val response = api.getNewsFeed(
                pageSize = params.loadSize.toString(),
                page = page.toString(),
                apiKey = BuildConfig.API_KEY
            )

            if (response.isSuccessful && response.body() != null) {
                val articles = response.body()?.articles.orEmpty()
                LoadResult.Page(
                    data = articles,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (articles.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception(response.errorBody()?.string() ?: response.message()))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}