package com.example.cryptotracker.feature_crypto.domain.repository

import com.example.cryptotracker.core.domain.util.NetworkError
import com.example.cryptotracker.core.domain.util.Result
import com.example.cryptotracker.feature_crypto.domain.model.CoinModel
import com.example.cryptotracker.feature_crypto.domain.model.CoinPrice
import java.time.ZonedDateTime

/*
The function which needs to be implemented are bundled here.
Implementation are done in the data layer
 */

interface CoinDataSource {

    suspend fun getCoins(): Result<List<CoinModel>, NetworkError>

    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>,NetworkError>
}