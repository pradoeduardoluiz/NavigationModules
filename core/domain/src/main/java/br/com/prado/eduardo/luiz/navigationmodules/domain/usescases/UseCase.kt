package br.com.prado.eduardo.luiz.navigationmodules.domain.usescases

import br.com.prado.eduardo.luiz.navigationmodules.common.NetworkErrorHandler
import br.com.prado.eduardo.luiz.navigationmodules.common.NetworkError
import br.com.prado.eduardo.luiz.navigationmodules.domain.UseCaseResponse
import kotlinx.coroutines.*

abstract class UseCase<Type, in Params>(
    private val networkErrorHandler: NetworkErrorHandler
) where Type : Any {

    companion object {
        private const val TAG = "UseCase"
    }

    abstract suspend fun run(params: Params?): Type

    fun invoke(
        scope: CoroutineScope,
        params: Params? = null,
        response: (UseCaseResponse<Type>)
    ) {
        val backgroundJob: Deferred<Type> = scope.async { run(params) }

        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            val error = networkErrorHandler.traceErrorException(exception)
            when (error.errorStatus) {
                NetworkError.ErrorStatus.NO_CONNECTION,
                NetworkError.ErrorStatus.TIMEOUT -> response.onError(exception as Exception)
                else -> {
                    exception.printStackTrace()
                    //response.onFailure(error)
                }
            }
        }

        scope.launch(exceptionHandler) {
            backgroundJob.await().let {
                try {
                    response.onSuccess(it)
                } catch (e: Exception) {
                    e.printStackTrace()
                    //response.onFailure(networkErrorHandler.traceErrorException(e))
                }
                response.onComplete()
            }
        }
    }
}