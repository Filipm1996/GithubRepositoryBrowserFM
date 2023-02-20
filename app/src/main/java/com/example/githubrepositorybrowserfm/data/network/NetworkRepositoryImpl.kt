package com.example.githubrepositorybrowserfm.data.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.githubrepositorybrowserfm.GetRepositoryQuery
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(private val apolloClient: ApolloClient) :
    NetworkRepository {

    override suspend fun getRepositories(user : String): Flow<ApolloResponse<GetRepositoryQuery.Data>> =
        apolloClient.query(query = GetRepositoryQuery(user)).toFlow()
}