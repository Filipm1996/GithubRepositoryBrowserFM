package com.example.githubrepositorybrowserfm.data.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.example.githubrepositorybrowserfm.GetRepositoryQuery
import com.example.githubrepositorybrowserfm.const.Const

class RepositoryPagingSource(
    private val apolloClient: ApolloClient
) : PagingSource<String, GetRepositoryQuery.Node>() {
    private var loadedItems = 0
    override fun getRefreshKey(state: PagingState<String, GetRepositoryQuery.Node>): String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, GetRepositoryQuery.Node> {
        if (params is LoadParams.Refresh) loadedItems = 0

        try {
            val repositoryList: GetRepositoryQuery.Data =
                apolloClient
                    .query(
                        GetRepositoryQuery(
                            Const.USER,
                            first = 10,
                            after = Optional.presentIfNotNull(params.key),
                        )
                    )
                    .execute()
                    .dataAssertNoErrors
            val data = repositoryList.repositoryOwner.repositories.edges
            loadedItems += data.size
            return LoadResult.Page(
                data = data.map { it!!.node },
                prevKey = null,
                nextKey = data.lastOrNull()?.cursor,
                itemsAfter = repositoryList.repositoryOwner.repositories.totalCount - loadedItems
            )
        } catch (e: Exception) {
            Log.i(e.message, "Could not fetch user repository list")
            return LoadResult.Error(e)
        }
    }
}