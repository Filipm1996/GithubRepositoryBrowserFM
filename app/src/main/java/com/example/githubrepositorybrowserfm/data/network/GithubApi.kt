package com.example.githubrepositorybrowserfm.data.network

import android.os.Looper
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.githubrepositorybrowserfm.BuildConfig
import com.example.githubrepositorybrowserfm.const.Const
import okhttp3.OkHttpClient

class GithubApi {
    fun getApolloClient(): ApolloClient {
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
}