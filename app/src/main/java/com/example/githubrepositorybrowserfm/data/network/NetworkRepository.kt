package com.example.githubrepositorybrowserfm.data.network

import androidx.paging.PagingData
import com.example.githubrepositorybrowserfm.GetRepositoryQuery
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    fun getRepositories(user: String): Flow<PagingData<GetRepositoryQuery.Node>>
}