package com.example.cryptotracker.feature_crypto.data.networking.data_source

import com.example.cryptotracker.BuildConfig
import com.example.cryptotracker.core.data.networking.constructUrl
import com.example.cryptotracker.core.data.networking.safeCall
import com.example.cryptotracker.core.domain.util.NetworkError
import com.example.cryptotracker.core.domain.util.Result
import com.example.cryptotracker.core.domain.util.map
import com.example.cryptotracker.feature_crypto.data.networking.dto.CoinHistoryDto
import com.example.cryptotracker.feature_crypto.data.networking.dto.CoinsResponseDto
import com.example.cryptotracker.feature_crypto.data.networking.mappers.toCoinModel
import com.example.cryptotracker.feature_crypto.data.networking.mappers.toCoinPrice
import com.example.cryptotracker.feature_crypto.domain.model.CoinModel
import com.example.cryptotracker.feature_crypto.domain.model.CoinPrice
import com.example.cryptotracker.feature_crypto.domain.repository.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {

    private val apiKey = BuildConfig.COINCAP_API_KEY

    override suspend fun getCoins(): Result<List<CoinModel>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            ) {
                header("Authorization", "Bearer $apiKey")
            }
        }.map { response->
            response.data.map {
                it.toCoinModel()
            }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {

        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()


        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                header("Authorization", "Bearer $apiKey")
                parameter("interval", "h6")
                parameter("start",startMillis)
                parameter("end",endMillis)
            }
        }.map { response ->
            response.data.map { coinPriceDto ->
                coinPriceDto.toCoinPrice()
            }
        }
    }

}