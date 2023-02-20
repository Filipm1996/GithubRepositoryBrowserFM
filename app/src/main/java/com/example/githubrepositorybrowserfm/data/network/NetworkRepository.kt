package com.example.githubrepositorybrowserfm.data.network

import com.apollographql.apollo3.api.ApolloResponse
import com.example.githubrepositorybrowserfm.GetRepositoryQuery
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun getRepositories(user : String) : Flow<ApolloResponse<GetRepositoryQuery.Data>>
}