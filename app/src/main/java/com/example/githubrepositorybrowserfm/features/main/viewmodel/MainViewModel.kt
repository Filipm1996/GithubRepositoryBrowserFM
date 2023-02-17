package com.example.githubrepositorybrowserfm.features.main.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.githubrepositorybrowserfm.data.network.NetworkRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.githubrepositorybrowserfm.GetRepositoriesQuery
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val networkRepository: NetworkRepositoryImpl) : ViewModel() {

    val listOfRepositories = mutableStateListOf<GetRepositoriesQuery.Data>()
    val errorMessage = mutableStateOf("")

    init {
        getRepositories()
    }

    private fun getRepositories() {
        viewModelScope.launch {
            try {
                val listOfRepo = networkRepository.getRepositories().dataAssertNoErrors
                listOfRepositories.add(listOfRepo)
            } catch (e: ApolloException) {
                errorMessage.value = e.message ?: "Nie udało się pobrać danych"
            }
        }
    }
}