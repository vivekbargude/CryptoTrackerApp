package com.example.cryptotracker.core.data.networking

import com.example.cryptotracker.BuildConfig

/*
1. We pass the base url
2. We pass the relative path e.g. /assets
2. We pass the relative path e.g. assets
 */
fun constructUrl(url: String): String {
    return when{
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }
}