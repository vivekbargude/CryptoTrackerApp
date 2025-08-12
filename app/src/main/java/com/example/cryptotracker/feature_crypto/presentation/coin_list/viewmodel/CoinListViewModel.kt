package com.example.cryptotracker.feature_crypto.presentation.coin_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.core.domain.util.onError
import com.example.cryptotracker.core.domain.util.onSuccess
import com.example.cryptotracker.feature_crypto.domain.repository.CoinDataSource
import com.example.cryptotracker.feature_crypto.presentation.coin_list.CoinListAction
import com.example.cryptotracker.feature_crypto.presentation.coin_list.state.CoinListState
import com.example.cryptotracker.feature_crypto.presentation.model.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
): ViewModel() {

    //state handling
    private val _state = MutableStateFlow(CoinListState())
    val state = _state  //mapping our mutable state to read only state for ui
        .onStart { //when the compose ui starts collecting flow or you can say listening for the data or state
            loadCoins()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L), //time for re fetching the new data
            CoinListState()
        )

    fun onAction(action: CoinListAction) {
        when(action) {
            is CoinListAction.OnCoinClick -> {

            }
            CoinListAction.OnRefresh -> {
                loadCoins()
            }
        }
    }


    private fun loadCoins() {
        viewModelScope.launch {
            //make the state loading
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            //start fetching data
            coinDataSource
                .getCoins()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { coin->
                                coin.toCoinUi()
                            }
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
        }
    }
}