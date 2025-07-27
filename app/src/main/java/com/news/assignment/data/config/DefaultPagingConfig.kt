package com.news.assignment.data.config

import androidx.paging.PagingConfig

object DefaultPagingConfig {
    val newsFeedPagingConfig = PagingConfig(
        pageSize = 20,
        initialLoadSize = 20,
        prefetchDistance = 4,
        enablePlaceholders = false
    )
}
