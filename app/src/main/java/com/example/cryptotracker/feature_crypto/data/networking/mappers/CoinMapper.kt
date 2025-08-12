package com.example.cryptotracker.feature_crypto.data.networking.mappers

import com.example.cryptotracker.feature_crypto.data.networking.dto.CoinDto
import com.example.cryptotracker.feature_crypto.domain.model.CoinModel

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