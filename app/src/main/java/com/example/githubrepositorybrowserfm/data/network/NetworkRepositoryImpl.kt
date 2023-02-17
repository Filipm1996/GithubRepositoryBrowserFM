package com.example.githubrepositorybrowserfm.data.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.githubrepositorybrowserfm.GetRepositoriesQuery
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(private val apolloClient: ApolloClient) :
    NetworkRepository {

    override suspend fun getRepositories(): ApolloResponse<GetRepositoriesQuery.Data> =
        apolloClient.query(query = GetRepositoriesQuery()).execute()
}