package com.example.githubrepositorybrowserfm.data

import com.apollographql.apollo3.api.ApolloResponse


interface NetworkRepository {

    fun getAllRepositories(): ApolloResponse<GetRepositoriesQuery.Data> {
        return
    }
}