package com.example.cryptotracker.feature_crypto.presentation.coin_list

import com.example.cryptotracker.feature_crypto.presentation.model.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
    data object OnRefresh: CoinListAction
}