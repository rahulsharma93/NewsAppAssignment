package com.news.assignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.news.assignment.domain.model.NewsArticle
import com.news.assignment.domain.usecase.GetNewsFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    getNewsFeedUseCase: GetNewsFeedUseCase
) : ViewModel() {

    val pagedNews: Flow<PagingData<NewsArticle>> = getNewsFeedUseCase()
        .cachedIn(viewModelScope)
}