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
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubrepositorybrowserfm.R
import com.example.githubrepositorybrowserfm.const.Const
import com.example.githubrepositorybrowserfm.data.entities.RepositoryInfo
import com.example.githubrepositorybrowserfm.ui.theme.Typography
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow

@Composable
fun MainScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    repositories: Flow<PagingData<RepositoryInfo>>
) {
    val context = LocalContext.current
    val listOfRepo = repositories.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues),
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
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.margin_0_5x))
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            when (val state = listOfRepo.loadState.prepend) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                        }
                    }
                }
                is LoadState.Error -> {
                    Toast.makeText(
                        context,
                        state.error.message ?: "Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            when (val state = listOfRepo.loadState.refresh) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                        }
                    }
                }
                is LoadState.Error -> {
                    Toast.makeText(
                        context,
                        state.error.message ?: "Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            items(listOfRepo.itemCount) { position ->
                val item = listOfRepo[position]
                if (item != null) {
                    RepositoryItem(repositoryInfo = item, onItemClick = { repoInfo ->
                        val json = Uri.encode(Gson().toJson(repoInfo))
                        navController.navigate("detail/$json")
                    }
                    )
                }
            }
        }
    }
}
