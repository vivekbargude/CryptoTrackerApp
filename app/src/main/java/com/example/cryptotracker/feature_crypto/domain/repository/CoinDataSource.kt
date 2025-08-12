package com.example.cryptotracker.feature_crypto.domain.repository

import com.example.cryptotracker.core.domain.util.NetworkError
import com.example.cryptotracker.core.domain.util.Result
import com.example.cryptotracker.feature_crypto.domain.model.CoinModel

/*
The function which needs to be implemented are bundled here.
Implementation are done in the data layer
 */
interface CoinDataSource {

    suspend fun getCoins(): Result<List<CoinModel>, NetworkError>
}