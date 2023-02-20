package com.example.githubrepositorybrowserfm.features.main

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubrepositorybrowserfm.R
import com.example.githubrepositorybrowserfm.const.Const
import com.example.githubrepositorybrowserfm.features.main.viewmodel.MainViewModel
import com.example.githubrepositorybrowserfm.ui.theme.Typography
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson

@Composable
fun MainScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    if (viewModel.errorMessage.value != "") {
        Toast.makeText(context, viewModel.errorMessage.value, Toast.LENGTH_SHORT).show()
        viewModel.errorMessage.value = ""
    }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.isRefreshing.value)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = viewModel::refresh,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painterResource(id = R.drawable.ic_baseline_person_24),
                contentDescription = "person avatar",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_0_5x))
            )
            Text(
                text = Const.USER,
                style = Typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_0_5x))
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(viewModel.listOfRepositories.size) {
                    val repository = viewModel.listOfRepositories[it]
                    RepositoryItem(repositoryInfo = repository, onItemClick = { repoInfo ->
                        val json = Uri.encode(Gson().toJson(repoInfo))
                        navController.navigate("detail/$json")
                    }
                    )
                }
            }
        }
    }
    if (viewModel.isLoading.value) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            strokeWidth = 10.dp
        )
    }
}