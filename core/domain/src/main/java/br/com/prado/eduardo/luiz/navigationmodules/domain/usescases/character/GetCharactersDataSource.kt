package br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.character

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import br.com.prado.eduardo.luiz.navigationmodules.common.NetworkError
import br.com.prado.eduardo.luiz.navigationmodules.common.Resource
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import br.com.prado.eduardo.luiz.navigationmodules.data.models.CharacterResponse
import br.com.prado.eduardo.luiz.navigationmodules.domain.UseCaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GetCharactersDataSource(
    private val scope: CoroutineScope,
    private val charactersUseCase: GetCharacters,
    private val resource: MutableLiveData<Resource<Int>>
) :
    PageKeyedDataSource<Int, Character>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        val page = 1
        invokeUseCase(page, params.requestedLoadSize, resource, onSuccess = {
            callback.onResult(it.characters, null, page + 1)
        }, onError = {
            callback.onError(it)
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        val page = params.key
        invokeUseCase(page, params.requestedLoadSize, resource, onSuccess = {
            callback.onResult(it.characters, if (it.info.next.isNotEmpty()) page + 1 else null)
        }, onError = {
            callback.onError(it)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        val page = params.key
        invokeUseCase(page, params.requestedLoadSize, resource, onSuccess = {
            callback.onResult(it.characters, if (it.info.prev.isNotEmpty()) page - 1 else null)
        }, onError = {
            callback.onError(it)
        })
    }

    private fun invokeUseCase(
        page: Int,
        pageSize: Int,
        resource: MutableLiveData<Resource<Int>>,
        onSuccess: (CharacterResponse) -> Unit,
        onError: (exception: Exception) -> Unit
    ) {
        scope.launch {

            resource.value = Resource.Loading

            charactersUseCase.invoke(
                scope = this,
                response = object : UseCaseResponse<CharacterResponse> {
                    override fun onComplete() {
                        resource.value = Resource.Complete
                    }

                    override fun onSuccess(result: CharacterResponse) {
                        resource.value = Resource.Success(result.characters.size)
                        onSuccess.invoke(result)
                    }

                    override fun onFailure(error: NetworkError) {
                        resource.value = Resource.Error.Api(error)
                    }

                    override fun onError(exception: Exception) {
                        resource.value = Resource.Error.Connection(exception)
                    }
                }, params = page
            )
        }
    }
}