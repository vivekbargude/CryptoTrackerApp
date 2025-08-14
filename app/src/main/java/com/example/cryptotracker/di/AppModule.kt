package com.example.cryptotracker.di

import com.example.cryptotracker.core.data.networking.HttpClientFactory
import com.example.cryptotracker.feature_crypto.data.networking.data_source.RemoteCoinDataSource
import com.example.cryptotracker.feature_crypto.domain.repository.CoinDataSource
import com.example.cryptotracker.feature_crypto.presentation.coin_list.viewmodel.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single {
        HttpClientFactory.create(
            CIO.create()
        )
    }

    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}