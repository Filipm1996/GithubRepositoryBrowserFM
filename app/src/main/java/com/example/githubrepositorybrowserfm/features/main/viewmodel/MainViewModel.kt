package com.example.githubrepositorybrowserfm.features.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.githubrepositorybrowserfm.GetRepositoryQuery
import com.example.githubrepositorybrowserfm.const.Const
import com.example.githubrepositorybrowserfm.data.entities.RepositoryInfo
import com.example.githubrepositorybrowserfm.data.network.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    networkRepository: NetworkRepository
) : ViewModel() {

    val repositories: Flow<PagingData<RepositoryInfo>> =
        networkRepository.getRepositories(Const.USER).map { response ->
            response.map { node ->
                convertToRepositoryInfo(node)
            }
        }.cachedIn(viewModelScope)

    private fun convertToRepositoryInfo(node: GetRepositoryQuery.Node): RepositoryInfo {
        return RepositoryInfo(
            name = node.nodeFields.name,
            description = node.nodeFields.description,
            commitsQty = node.nodeFields.onRepository.defaultBranchRef?.target?.onCommit?.history?.totalCount
                ?: 0,
            issuesQty = node.nodeFields.issues.totalCount
        )
    }
}