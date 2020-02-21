package br.com.prado.eduardo.luiz.navigationmodules.character.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.prado.eduardo.luiz.navigationmodules.common.NetworkError
import br.com.prado.eduardo.luiz.navigationmodules.common.Resource
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import br.com.prado.eduardo.luiz.navigationmodules.domain.UseCaseResponse
import br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.character.GetCharacters

class CharactersListViewModel(private val charactersUseCase: GetCharacters) : ViewModel() {

    private val characters: MutableLiveData<Resource<List<Character>>> by lazy {
        MutableLiveData<Resource<List<Character>>>()
    }

    fun getCharacters(): LiveData<Resource<List<Character>>> = characters

    fun fetchCharacters() {
        characters.value = Resource.Loading

        charactersUseCase.invoke(
            scope = viewModelScope,
            response = object : UseCaseResponse<List<Character>> {

                override fun onComplete() {
                    characters.value = Resource.Complete
                }

                override fun onSuccess(result: List<Character>) {
                    characters.value = Resource.Success(result)
                }

                override fun onFailure(error: NetworkError) {
                    characters.value = Resource.Error.Api(error)
                }

                override fun onError(exception: Exception) {
                    characters.value = Resource.Error.Connection(exception)
                }
            }
        )
    }
}