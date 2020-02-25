package br.com.prado.eduardo.luiz.navigationmodules.domain

import br.com.prado.eduardo.luiz.navigationmodules.common.NetworkError


interface UseCaseResponse<Type> {

    fun onComplete()

    fun onSuccess(result: Type)

    fun onFailure(error: NetworkError)

    fun onError(exception: Exception)

}