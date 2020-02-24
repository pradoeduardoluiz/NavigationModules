package br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.character

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import br.com.prado.eduardo.luiz.navigationmodules.common.NetworkError
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import br.com.prado.eduardo.luiz.navigationmodules.data.models.CharacterResponse
import br.com.prado.eduardo.luiz.navigationmodules.domain.UseCaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GetCharactersDataSource(
    private val scope: CoroutineScope,
    private val charactersUseCase: GetCharacters,
    private val isLoading: MutableLiveData<Boolean>
) :
    PageKeyedDataSource<Int, Character>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        val page = 1
        invokeUseCase(page, params.requestedLoadSize, isLoading, onSuccess = {
            callback.onResult(it.characters, null, page + 1)
        }, onError = {
            callback.onError(it)
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        val page = params.key
        invokeUseCase(page, params.requestedLoadSize, isLoading, onSuccess = {
            callback.onResult(it.characters, if (it.info.next.isNotEmpty()) page + 1 else null)
        }, onError = {
            callback.onError(it)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        val page = params.key
        invokeUseCase(page, params.requestedLoadSize, isLoading, onSuccess = {
            callback.onResult(it.characters, if (it.info.prev.isNotEmpty()) page - 1 else null)
        }, onError = {
            callback.onError(it)
        })
    }

    private fun invokeUseCase(
        page: Int,
        pageSize: Int,
        isLoading: MutableLiveData<Boolean>,
        onSuccess: (CharacterResponse) -> Unit,
        onError: (exception: Exception) -> Unit
    ) {
        scope.launch {

            isLoading.postValue(true)

            charactersUseCase.invoke(
                scope = this,
                response = object : UseCaseResponse<CharacterResponse> {
                    override fun onComplete() {
                        isLoading.postValue(false)
                    }

                    override fun onSuccess(result: CharacterResponse) {
                        onSuccess.invoke(result)
                    }

                    override fun onFailure(error: NetworkError) {
                        isLoading.postValue(false)
                    }

                    override fun onError(exception: Exception) {
                        isLoading.postValue(false)
                        onError.invoke(exception)
                    }
                }, params = page
            )
        }
    }
}