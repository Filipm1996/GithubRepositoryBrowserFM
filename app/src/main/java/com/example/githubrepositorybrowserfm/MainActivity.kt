package com.example.githubrepositorybrowserfm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.example.githubrepositorybrowserfm.ui.theme.GithubRepositoryBrowserFMTheme
import com.example.githubrepositorybrowserfm.ui.theme.Typography
import com.example.githubrepositorybrowserfm.ui.theme.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubRepositoryBrowserFMTheme {
                val navController = rememberNavController()
                Scaffold(
                    content = { paddingVaules ->
                        Navigation(navController = navController, paddingVaules)
                    },
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = stringResource(id = R.string.app_name),
                                    style = Typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSecondary
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    onBackPressed()
                                }) {
                                    Icon(
                                        Icons.Filled.ArrowBack, "backIcon",
                                        tint = MaterialTheme.colorScheme.onSecondary
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                titleContentColor = MaterialTheme.colorScheme.onSecondary,
                            ),
                        )
                    })
            }
        }
    }
}
