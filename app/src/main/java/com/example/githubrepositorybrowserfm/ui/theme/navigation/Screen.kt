package com.example.githubrepositorybrowserfm.ui.theme.navigation

sealed class Screen(val route : String) {
    object MainScreen : Screen("main")
    object DetailScreen : Screen("detail")
}
