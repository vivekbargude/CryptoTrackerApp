package com.example.cryptotracker.feature_crypto.domain.model

import java.time.ZonedDateTime

data class CoinPrice(
    val priceUsd: Double,
    val dateTime: ZonedDateTime,
)
