package com.news.assignment.presentation.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.news.assignment.presentation.viewmodel.NewsViewModel

@Composable
fun NewsNavHost(navController: NavHostController, viewModel: NewsViewModel) {
    NavHost(navController = navController, startDestination = "news_feed") {
        composable("news_feed") {
            NewsFeedScreen(
                viewModel = viewModel,
                onArticleClick = { article ->

                }
            )
        }
    }
}
