package com.example.cryptotracker.core.data.networking

import com.example.cryptotracker.BuildConfig

/*
1. We pass the base url
2. We pass the relative path e.g. /assets
2. We pass the relative path e.g. assets
 */
fun constructUrl(url: String): String {
    val baseUrl = BuildConfig.BASE_URL
    val apiKeyParam = "apiKey=${BuildConfig.COINCAP_API_KEY}"

    // Build the full path
    val fullUrl = when {
        url.contains(baseUrl) -> url
        url.startsWith("/") -> baseUrl + url.drop(1)
        else -> baseUrl + url
    }

    // Append the API key properly (respect existing query params)
    return if (fullUrl.contains("?")) {
        "$fullUrl&$apiKeyParam"
    } else {
        "$fullUrl?$apiKeyParam"
    }
}
