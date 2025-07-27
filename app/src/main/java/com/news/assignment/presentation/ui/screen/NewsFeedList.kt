package com.news.assignment.presentation.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.news.assignment.R
import com.news.assignment.domain.model.NewsArticle

@Composable
fun NewsFeedList(
    newsItems: LazyPagingItems<NewsArticle>,
    onArticleClick: (NewsArticle) -> Unit,
    showLoadingItem: Boolean = true
) {
    val onClickState = rememberUpdatedState(onArticleClick)

    LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        item {
            Text(
                text = stringResource(R.string.latest_news),
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.headlineMedium,
            )
        }

        items(newsItems.itemCount) { index ->
            newsItems[index]?.let { article ->
                NewsFeedListItem(item = article, onClick = { onClickState.value(it) })
            }
        }

        newsItems.apply {
            when {
                loadState.refresh is LoadState.Loading && showLoadingItem -> item {
                    LoadingItem(stringResource(R.string.loading_text))
                }

                loadState.append is LoadState.Loading -> item {
                    LoadingItem(stringResource(R.string.loading_more_text))
                }

                loadState.refresh is LoadState.Error -> item {
                    val error = loadState.refresh as LoadState.Error
                    ErrorItem(
                        message = error.error.message ?: stringResource(R.string.something_went_wrong),
                        onRetryClick = { retry() }
                    )
                }

                loadState.append is LoadState.Error -> item {
                    val error = loadState.append as LoadState.Error
                    ErrorItem(
                        message = error.error.message ?: stringResource(R.string.failed_to_load_more),
                        onRetryClick = { retry() }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NewsFeedListPreview() {
    val articles = listOf(
        NewsArticle(
            title = "Title 1",
            articleUrl = "https://example.com/1",
            author = null,
            description = "This is a descriptive text for the article, providing a brief summary of its content. It should be long enough to give context but concise.",
            imageUrl = null,
            publishedDate = "2025-07-26",
            sourceName = "News Source"
        )
    )

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(articles) { article ->
            NewsFeedListItem(item = article, onClick = {})
        }
    }
}