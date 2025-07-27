package com.news.assignment.domain.usecase

import androidx.paging.PagingData
import com.news.assignment.domain.model.NewsArticle
import com.news.assignment.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsFeedUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(): Flow<PagingData<NewsArticle>> {
        return repository.fetchNewsFeed()
    }
}