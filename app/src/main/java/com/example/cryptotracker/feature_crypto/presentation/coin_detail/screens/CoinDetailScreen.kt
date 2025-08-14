@file:Suppress("NAME_SHADOWING")

package com.example.cryptotracker.feature_crypto.presentation.coin_detail.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptotracker.R
import com.example.cryptotracker.feature_crypto.presentation.coin_detail.components.InfoCard
import com.example.cryptotracker.feature_crypto.presentation.coin_list.state.CoinListState
import com.example.cryptotracker.feature_crypto.presentation.model.toDisplayableNumber
import com.example.cryptotracker.ui.theme.greenBackground

/*
As we are going to have a layout where left side is list screen and right is detail hence we will pass the same state that we passed
to the list screen.
 */


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    state: CoinListState,
    modifier: Modifier = Modifier
) {
    val contentColor = if(isSystemInDarkTheme()) Color.White else Color.Black

    if(state.isLoading) {
        Box (
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else if(state.selectedCoin != null) { //some coin is selected
        val coin = state.selectedCoin

        Column (
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Icon(
                imageVector = ImageVector.vectorResource(
                    id = coin.iconResource
                ),
                contentDescription = coin.name,
                modifier = Modifier
                    .size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = coin.name,
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = contentColor
            )

            Text(
                text = coin.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                color = contentColor
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                InfoCard(
                    title = stringResource(id = R.string.market_cap_usd),
                    formattedText = "$ ${coin.marketCapUsd.formatted}",
                    icon = ImageVector.vectorResource(R.drawable.stock)
                )

                InfoCard(
                    title = stringResource(id = R.string.price),
                    formattedText = "$ ${coin.priceUsd.formatted}",
                    icon = ImageVector.vectorResource(R.drawable.dollar)
                )

                val absoluteChangeFormatted = (coin.priceUsd.value* (coin.changePercent24Hr.value/100))
                    .toDisplayableNumber()

                val isPositive = coin.changePercent24Hr.value > 0.0
                val contentColor = if(isPositive){
                    if(isSystemInDarkTheme())
                        Color.Green
                    else
                        greenBackground
                } else {
                    MaterialTheme.colorScheme.error
                }
                InfoCard(
                    title = stringResource(id = R.string.change_last_24h),
                    formattedText = absoluteChangeFormatted.formatted,
                    icon = if(isPositive) {
                        ImageVector.vectorResource(id = R.drawable.trending)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.trending_down)
                    },
                    contentColor = contentColor
                )

            }
        }
    }
}