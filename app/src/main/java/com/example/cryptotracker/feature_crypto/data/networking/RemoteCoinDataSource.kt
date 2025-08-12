package com.example.cryptotracker.feature_crypto.data.networking

import com.example.cryptotracker.core.data.networking.constructUrl
import com.example.cryptotracker.core.data.networking.safeCall
import com.example.cryptotracker.core.domain.util.NetworkError
import com.example.cryptotracker.core.domain.util.Result
import com.example.cryptotracker.core.domain.util.map
import com.example.cryptotracker.feature_crypto.data.networking.dto.CoinsResponseDto
import com.example.cryptotracker.feature_crypto.data.networking.mappers.toCoinModel
import com.example.cryptotracker.feature_crypto.domain.model.CoinModel
import com.example.cryptotracker.feature_crypto.domain.repository.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {

    override suspend fun getCoins(): Result<List<CoinModel>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response->
            response.data.map {
                it.toCoinModel()
            }
        }
    }

}