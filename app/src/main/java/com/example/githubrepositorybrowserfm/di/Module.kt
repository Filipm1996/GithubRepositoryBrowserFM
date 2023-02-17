package com.example.githubrepositorybrowserfm.di

import com.apollographql.apollo3.ApolloClient
import com.example.githubrepositorybrowserfm.data.GithubApi
import com.example.githubrepositorybrowserfm.data.network.NetworkRepository
import com.example.githubrepositorybrowserfm.data.network.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideApolloClient() : ApolloClient = GithubApi().getApolloClient()

    @Singleton
    @Provides
    fun provideNetworkRepository(
        apolloClient: ApolloClient
    ): NetworkRepository = NetworkRepositoryImpl(apolloClient)
}