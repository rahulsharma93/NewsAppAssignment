package com.news.assignment.presentation.ui.screen

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.news.assignment.presentation.viewmodel.NewsViewModel

@Composable
fun NewsNavHost(navController: NavHostController, viewModel: NewsViewModel) {
    NavHost(navController = navController, startDestination = "news_feed") {
        composable("news_feed") {
            NewsFeedScreen(
                viewModel = viewModel,
                onArticleClick = { article ->
                    navController.navigate("detail/${Uri.encode(article.articleUrl)}")
                }
            )
        }
        composable(
            route = "detail/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            NewsDetailScreen(url = url, navController = navController)
        }
    }
}
