package com.example.cryptotracker.feature_crypto.presentation.coin_list.state

import androidx.compose.runtime.Immutable
import com.example.cryptotracker.feature_crypto.presentation.model.CoinUi

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null
)
