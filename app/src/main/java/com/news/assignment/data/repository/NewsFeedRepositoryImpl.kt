package com.news.assignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.news.assignment.data.config.DefaultPagingConfig
import com.news.assignment.data.mapper.toDomain
import com.news.assignment.data.remote.NewsApiService
import com.news.assignment.data.remote.NewsPagingSource
import com.news.assignment.domain.model.NewsArticle
import com.news.assignment.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class NewsFeedRepositoryImpl @Inject constructor(private val api: NewsApiService):
    NewsFeedRepository {

    override fun fetchNewsFeed(): Flow<PagingData<NewsArticle>> {
        return Pager(
            config = DefaultPagingConfig.newsFeedPagingConfig,
            pagingSourceFactory = { NewsPagingSource(api) }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}