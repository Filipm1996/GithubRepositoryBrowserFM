package com.example.githubrepositorybrowserfm.di

import android.os.Looper
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.githubrepositorybrowserfm.BuildConfig
import com.example.githubrepositorybrowserfm.const.Const
import com.example.githubrepositorybrowserfm.data.network.NetworkRepository
import com.example.githubrepositorybrowserfm.data.network.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient {
        check(Looper.myLooper() == Looper.getMainLooper())

        val okHttpClient = OkHttpClient.Builder().build()
        return ApolloClient.Builder()
            .serverUrl(Const.SERVER_URL)
            .okHttpClient(okHttpClient)
            .addHttpHeader(
                Const.HEADER_AUTHORIZATION,
                "${Const.HEADER_AUTHORIZATION_BEARER} ${BuildConfig.GITHUB_OAUTH_KEY}"
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkRepository(
        apolloClient: ApolloClient
    ): NetworkRepository = NetworkRepositoryImpl(apolloClient)
}