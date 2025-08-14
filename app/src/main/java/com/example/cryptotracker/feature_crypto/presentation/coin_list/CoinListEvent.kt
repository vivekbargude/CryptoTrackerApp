package com.example.cryptotracker.feature_crypto.presentation.coin_list

import com.example.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent

}