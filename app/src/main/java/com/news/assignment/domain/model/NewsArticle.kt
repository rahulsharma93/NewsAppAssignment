package com.news.assignment.domain.model

data class NewsArticle(
    val title: String,
    val description: String,
    val imageUrl: String?,
    val articleUrl: String,
    val author: String?,
    val publishedDate: String,
    val sourceName: String
)
