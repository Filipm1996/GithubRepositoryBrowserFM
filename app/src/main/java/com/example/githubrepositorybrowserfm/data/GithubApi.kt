package com.example.githubrepositorybrowserfm.data

import android.os.Looper
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.githubrepositorybrowserfm.BuildConfig
import okhttp3.OkHttpClient

class GithubApi {
    fun getApolloClient(): ApolloClient {
        check(Looper.myLooper() == Looper.getMainLooper()) {
            "Only the main thread can get the apolloClient instance"
        }

        val okHttpClient = OkHttpClient.Builder().build()
        return ApolloClient.Builder()
            .serverUrl(SERVER_URL)
            .okHttpClient(okHttpClient)
            .addHttpHeader(
                HEADER_AUTHORIZATION,
                "$HEADER_AUTHORIZATION_BEARER ${BuildConfig.GITHUB_OAUTH_KEY}"
            )
            .build()
    }

    companion object {
        private const val SERVER_URL = "https://api.github.com/graphql"

        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_AUTHORIZATION_BEARER = "Bearer"
    }
}