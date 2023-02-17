package com.example.githubrepositorybrowserfm.features.main

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubrepositorybrowserfm.features.main.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    if(viewModel.errorMessage.value !="") {
        Toast.makeText(context, "Error" , Toast.LENGTH_SHORT).show()
    }
    if(viewModel.listOfRepositories.isNotEmpty()){
        viewModel.listOfRepositories.forEach {
            println(it.repositoryOwner?.repositories?.nodes)
        }
    }
}