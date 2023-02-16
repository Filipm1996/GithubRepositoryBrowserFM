package com.example.githubrepositorybrowserfm.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.githubrepositorybrowserfm.features.detail.DetailScreen
import com.example.githubrepositorybrowserfm.features.main.MainScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen()
        }
        composable(Screen.DetailScreen.route) {
            DetailScreen()
        }
    }
}