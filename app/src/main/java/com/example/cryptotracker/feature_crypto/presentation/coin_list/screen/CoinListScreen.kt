package com.example.cryptotracker.feature_crypto.presentation.coin_list.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cryptotracker.feature_crypto.presentation.coin_list.CoinListAction
import com.example.cryptotracker.feature_crypto.presentation.coin_list.components.CoinListItem
import com.example.cryptotracker.feature_crypto.presentation.coin_list.state.CoinListState

@Composable
fun CoinListScreen(
    state: CoinListState,
    onAction: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    if(state.isLoading) {
        Box (
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins) { coin->
                CoinListItem(
                    coinUi = coin,
                    onClick = {
                        onAction(
                            CoinListAction.OnCoinClick(
                                coinUi = coin
                            )
                        )
                    }
                )
                HorizontalDivider()
            }
        }
    }
}