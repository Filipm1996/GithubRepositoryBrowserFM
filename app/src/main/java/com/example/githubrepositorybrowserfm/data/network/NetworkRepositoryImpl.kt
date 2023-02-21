package com.example.githubrepositorybrowserfm.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo3.ApolloClient
import com.example.githubrepositorybrowserfm.GetRepositoryQuery
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(private val apolloClient: ApolloClient) :
    NetworkRepository {

    override fun getRepositories(user: String): Flow<PagingData<GetRepositoryQuery.Node>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                RepositoryPagingSource(apolloClient)
            }
        ).flow
}