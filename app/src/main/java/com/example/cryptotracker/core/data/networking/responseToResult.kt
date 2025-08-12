package com.example.cryptotracker.core.data.networking

import androidx.compose.ui.Modifier
import com.example.cryptotracker.core.domain.util.NetworkError
import com.example.cryptotracker.core.domain.util.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, NetworkError> {
    return when(response.status.value) {

        //success case then take data from http response and convert it to the result type of out domain(core) layer.
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch(e: NoTransformationFoundException) {
                Result.Error(NetworkError.SERIALIZATION_ERROR)
            }
        }

        //request timeout
        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)

        //to many requests
        429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)

        //server error
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)

        //rest all the cases
        else -> Result.Error(NetworkError.UNKNOWN)
    }
}