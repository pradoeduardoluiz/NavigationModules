package br.com.prado.eduardo.luiz.navigationmodules.common

/**
 * This class is designed to show different types of errors through error status & message
 **/
data class NetworkError(
    val message: String?,
    val code: Int?,
    var errorStatus: ErrorStatus
) {
    constructor(errorStatus: ErrorStatus) : this(null, null, errorStatus)

    constructor(message: String?, errorStatus: ErrorStatus) : this(message, null, errorStatus)

    companion object {
        private const val NO_CONNECTION_ERROR_MESSAGE = "No connection."
        private const val BAD_RESPONSE_ERROR_MESSAGE = "Bad response."
        private const val TIME_OUT_ERROR_MESSAGE = "Time out."
        private const val EMPTY_RESPONSE_ERROR_MESSAGE = "Empty response."
        private const val NOT_DEFINED_ERROR_MESSAGE = "Not defined."
        private const val UNAUTHORIZED_ERROR_MESSAGE = "Unauthorized."
        private const val SERVER_ERROR_MESSAGE = "Server Error."
        private const val FORBIDDEN_ERROR_MESSAGE = "Forbidden Error."
        private const val BAD_REQUEST_ERROR_MESSAGE = "Bad Request."
    }

    fun getErrorMessage(): String {
        return when (errorStatus) {
            ErrorStatus.NO_CONNECTION -> NO_CONNECTION_ERROR_MESSAGE
            ErrorStatus.BAD_RESPONSE -> BAD_RESPONSE_ERROR_MESSAGE
            ErrorStatus.TIMEOUT -> TIME_OUT_ERROR_MESSAGE
            ErrorStatus.EMPTY_RESPONSE -> EMPTY_RESPONSE_ERROR_MESSAGE
            ErrorStatus.NOT_DEFINED -> NOT_DEFINED_ERROR_MESSAGE
            ErrorStatus.UNAUTHORIZED -> UNAUTHORIZED_ERROR_MESSAGE
            ErrorStatus.SERVER_ERROR -> SERVER_ERROR_MESSAGE
            ErrorStatus.FORBIDDEN -> FORBIDDEN_ERROR_MESSAGE
            ErrorStatus.BAD_REQUEST -> BAD_REQUEST_ERROR_MESSAGE
        }
    }

    /**
     * various error status to know what happened if something goes wrong with a repository call
     */
    enum class ErrorStatus {
        /**
         * error in connecting to repository (Server or Database)
         */
        NO_CONNECTION,
        /**
         * error in getting value (Json Error, Server Error, etc)
         */
        BAD_RESPONSE,
        /**
         * Time out error
         */
        TIMEOUT,
        /**
         * no data available in repository
         */
        EMPTY_RESPONSE,
        /**
         * an unexpected error
         */
        NOT_DEFINED,
        /**
         * bad credential
         */
        UNAUTHORIZED,
        /**
         * internal server error
         */
        SERVER_ERROR,
        /**
         * unable to get the content
         */
        FORBIDDEN,
        /**
         * when the request does not work as expected
         */
        BAD_REQUEST
    }
}