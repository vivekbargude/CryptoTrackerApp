package com.example.cryptotracker.feature_crypto.data.networking.mappers

import com.example.cryptotracker.feature_crypto.data.networking.dto.CoinDto
import com.example.cryptotracker.feature_crypto.data.networking.dto.CoinPriceDto
import com.example.cryptotracker.feature_crypto.domain.model.CoinModel
import com.example.cryptotracker.feature_crypto.domain.model.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoinModel(): CoinModel {
    return CoinModel(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant.ofEpochMilli(
            time
        ).atZone(ZoneId.of("UTC"))
    )
}