package br.com.prado.eduardo.luiz.navigationmodules.common

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    object Complete : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    sealed class Error : Resource<Nothing>() {
        data class Api(val networkError: NetworkError) : Error()
        data class Connection(val exception: Exception) : Error()
    }
}
