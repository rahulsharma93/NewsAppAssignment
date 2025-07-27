package com.news.assignment.data.mapper

import com.news.assignment.data.model.Article
import com.news.assignment.domain.model.NewsArticle

fun Article.toDomain(): NewsArticle {
    return NewsArticle(
        title = title,
        description = description.orEmpty(),
        imageUrl = urlToImage,
        articleUrl = url,
        author = author ?: "Unknown Author",
        publishedDate = publishedAt,
        sourceName = source.name
    )
}
