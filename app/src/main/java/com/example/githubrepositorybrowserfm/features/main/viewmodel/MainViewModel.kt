package com.example.githubrepositorybrowserfm.features.main.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.githubrepositorybrowserfm.GetRepositoryQuery
import com.example.githubrepositorybrowserfm.const.Const
import com.example.githubrepositorybrowserfm.data.entities.RepositoryInfo
import com.example.githubrepositorybrowserfm.data.network.NetworkRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkRepository: NetworkRepositoryImpl
) : ViewModel() {

    val listOfRepositories = mutableStateListOf<RepositoryInfo>()
    val errorMessage = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    val isRefreshing = mutableStateOf(false)

    init {
        getRepositories()
    }

    fun refresh() {
        isRefreshing.value = true
        getRepositories()
    }

    private fun getRepositories() {
        viewModelScope.launch {
            listOfRepositories.clear()
            isLoading.value = true
            try {
                networkRepository.getRepositories(Const.USER).collect { response ->
                    val edges = response.data?.repositoryOwner?.repositories?.edges
                    if (edges?.isNotEmpty() == true) {
                        edges.forEach { edge ->
                            listOfRepositories.add(
                                convertToRepositoryInfo(edge!!)
                            )
                        }
                    } else {
                        errorMessage.value = "Nic nie znaleziono"
                    }
                    isLoading.value = false
                    isRefreshing.value = false
                }
            } catch (e: ApolloException) {
                errorMessage.value = e.message ?: "Nie udało się pobrać danych"
                isLoading.value = false
                isRefreshing.value = false
            }
        }
    }

    private fun convertToRepositoryInfo(edge: GetRepositoryQuery.Edge): RepositoryInfo {
        return RepositoryInfo(
            name = edge.node.nodeFields.name,
            description = edge.node.nodeFields.description,
            commitsQty = edge.node.nodeFields.onRepository.defaultBranchRef?.target?.onCommit?.history?.totalCount
                ?: 0,
            issuesQty = edge.node.nodeFields.issues.totalCount
        )
    }
}