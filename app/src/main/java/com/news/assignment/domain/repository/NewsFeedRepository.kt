package com.news.assignment.domain.repository

import androidx.paging.PagingData
import com.news.assignment.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsFeedRepository {
    fun fetchNewsFeed(): Flow<PagingData<NewsArticle>>
}