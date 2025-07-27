package com.news.assignment.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.news.assignment.domain.model.NewsArticle
import com.news.assignment.presentation.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsFeedScreen(
    viewModel: NewsViewModel,
    onArticleClick: (NewsArticle) -> Unit
) {
    val newsItems = viewModel.pagedNews.collectAsLazyPagingItems()
    var isUserRefreshing by remember { mutableStateOf(false) }

    val isRefreshing = newsItems.loadState.refresh is LoadState.Loading

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isUserRefreshing = true
            newsItems.refresh()
        }
    )

    LaunchedEffect(isRefreshing) {
        if (!isRefreshing) isUserRefreshing = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        NewsFeedList(
            newsItems = newsItems,
            onArticleClick = onArticleClick,
            showLoadingItem = !isUserRefreshing
        )

        if (isUserRefreshing) {
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}
