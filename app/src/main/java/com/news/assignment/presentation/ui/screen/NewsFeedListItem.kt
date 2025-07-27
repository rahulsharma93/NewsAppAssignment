package com.news.assignment.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.news.assignment.R
import com.news.assignment.domain.model.NewsArticle
import com.news.assignment.presentation.components.AsyncImage

@Composable
fun NewsFeedListItem(item: NewsArticle, onClick: (NewsArticle) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(item) }
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    item.title,
                    style = MaterialTheme.typography.titleMedium,
                )

                if (!item.description.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        item.description,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            if (!item.imageUrl.isNullOrEmpty()) {
                AsyncImage(
                    modifier = Modifier
                        .size(100.dp)
                        .weight(0.4f),
                    url = item.imageUrl,
                    contentScale = ContentScale.Crop,
                    imageModifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp)),
                    placeholder = R.drawable.no_photo_placeholder
                )
            }
        }
    }
}

@Preview
@Composable
private fun NewsFeedListItemPreview() {
    NewsFeedListItem(
        item = NewsArticle(
            title = "Title 1",
            articleUrl = "https://example.com/1",
            author = null,
            description = "This is a descriptive text for the article, providing a brief summary of its content. It should be long enough to give context but concise.",
            imageUrl = null,
            publishedDate = "2025-07-26",
            sourceName = "News Source"
        ),
        onClick = {}
    )
}