package com.example.githubrepositorybrowserfm.ui.theme.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.example.githubrepositorybrowserfm.data.entities.RepositoryInfo
import com.google.gson.Gson

class RepositoryNavType : NavType<RepositoryInfo>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): RepositoryInfo? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): RepositoryInfo {
        return Gson().fromJson(value, RepositoryInfo::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: RepositoryInfo) {
        bundle.putParcelable(key, value)
    }
}