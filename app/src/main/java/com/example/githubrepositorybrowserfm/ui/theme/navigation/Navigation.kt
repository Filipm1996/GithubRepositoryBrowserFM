package com.example.githubrepositorybrowserfm.ui.theme.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.PagingData
import com.example.githubrepositorybrowserfm.data.entities.RepositoryInfo
import com.example.githubrepositorybrowserfm.features.detail.DetailScreen
import com.example.githubrepositorybrowserfm.features.main.MainScreen
import kotlinx.coroutines.flow.Flow

@Composable
fun Navigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    repositories: Flow<PagingData<RepositoryInfo>>
) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController, paddingValues, repositories)
        }
        composable(
            Screen.DetailScreen.route,
            arguments = listOf(navArgument("repositoryInfo") {
                type = RepositoryNavType()
            })
        ) {
            val repositoryInfo = it.arguments?.getParcelable<RepositoryInfo>("repositoryInfo")
            DetailScreen(repositoryInfo!!, paddingValues)
        }
    }
}