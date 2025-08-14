package com.example.cryptotracker.core.presentation.util

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.cryptotracker.feature_crypto.presentation.coin_list.CoinListEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> ObserveAsEvents(
    events: Flow<T>,
    key1: Any? = null,
    key2: Any? = null,
    onEvent: (T) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner.lifecycle,key1,key2) {
        //to listen and observe the events in lifecycle aware manner such that we should stop listening when activity goes to background
        lifecycleOwner.repeatOnLifecycle(
            Lifecycle.State.STARTED
        ) {
            withContext(Dispatchers.Main.immediate) {
                //listening to each event send by the viewmodel
                events.collect(onEvent)
            }
        }
    }
}