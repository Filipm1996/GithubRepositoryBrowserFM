package com.example.githubrepositorybrowserfm.data.network

import com.apollographql.apollo3.api.ApolloResponse
import com.example.githubrepositorybrowserfm.GetRepositoriesQuery

interface NetworkRepository {

    suspend fun getRepositories() : ApolloResponse<GetRepositoriesQuery.Data>
}