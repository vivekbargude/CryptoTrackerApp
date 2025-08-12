package com.example.cryptotracker.feature_crypto.presentation.model

import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.example.cryptotracker.feature_crypto.domain.model.CoinModel
import com.example.cryptotracker.core.presentation.util.getDrawableIdForCoin
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconResource: Int
)


//to convert the actual value to formatted value inorder to display to the UI
data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun CoinModel.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        priceUsd = priceUsd.toDisplayableNumber(),
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        iconResource = getDrawableIdForCoin(symbol = symbol)
    )
}


fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}
