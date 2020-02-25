package br.com.prado.eduardo.luiz.navigationmodules.common

import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

/**
 * This class trace exceptions(api call or parse data or connection errors) &
 * depending on what exception returns a [NetworkError]
 *
 * */
class NetworkErrorHandler {

    companion object {
        private const val TAG = "NetworkErrorHandler"
    }

    fun traceErrorException(throwable: Throwable?): NetworkError {
        val errorModel: NetworkError? = when (throwable) {
            // if throwable is an instance of HttpException
            // then attempt to parse error data from response body
            is HttpException -> {
                // handle UNAUTHORIZED situation (when token expired)
                when {
                    throwable.code() == HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        NetworkError(
                            throwable.message(),
                            throwable.code(),
                            NetworkError.ErrorStatus.UNAUTHORIZED
                        )
                    }
                    throwable.code() == HttpURLConnection.HTTP_FORBIDDEN -> {
                        NetworkError(
                            throwable.message(),
                            throwable.code(),
                            NetworkError.ErrorStatus.FORBIDDEN
                        )
                    }
                    else -> {
                        getHttpError(throwable.response()?.errorBody())
                    }
                }
            }

            // handle api call timeout error
            is SocketTimeoutException -> {
                NetworkError(
                    throwable.message,
                    NetworkError.ErrorStatus.TIMEOUT
                )
            }

            // handle connection error
            is IOException -> {
                NetworkError(
                    throwable.message,
                    NetworkError.ErrorStatus.NO_CONNECTION
                )
            }
            else -> null
        }
        return errorModel ?: NetworkError(
            "No Defined Error!",
            0,
            NetworkError.ErrorStatus.BAD_RESPONSE
        )
    }

    /**
     * Attempts to parse http response body and get error data from it
     *
     * @param body retrofit response body
     * @return returns an instance of [NetworkError] with parsed data or NOT_DEFINED status
     */
    private fun getHttpError(body: ResponseBody?): NetworkError {
        return try {
            // use response body to get error detail
            NetworkError(
                formatFailureMessage(body?.string()),
                HttpURLConnection.HTTP_BAD_REQUEST,
                NetworkError.ErrorStatus.BAD_REQUEST
            )
        } catch (e: Throwable) {
            e.printStackTrace()
            NetworkError(
                message = e.message,
                errorStatus = NetworkError.ErrorStatus.NOT_DEFINED
            )
        }

    }

    private fun formatFailureMessage(message: String?): String {
        return try {
            val jsonElement = JsonParser.parseString(message)
            val jsonArray = jsonElement.asJsonArray

            jsonArray.run {
                (0 until size()).asSequence().map { index ->
                    get(index).asString
                }.joinToString("\n")
            }
        } catch (e: JsonParseException) {
            e.message ?: "Unknown error"
        }
    }
}