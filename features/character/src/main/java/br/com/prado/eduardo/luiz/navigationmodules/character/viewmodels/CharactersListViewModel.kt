package br.com.prado.eduardo.luiz.navigationmodules.character.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.prado.eduardo.luiz.navigationmodules.common.Resource
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.character.GetCharacters
import br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.character.GetCharactersDataSource
import br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.character.GetCharactersDataSourceFactory

class CharactersListViewModel(
    private val charactersUseCase: GetCharacters
) : ViewModel() {

    private val resource: MutableLiveData<Resource<Int>> by lazy {
        MutableLiveData<Resource<Int>>()
    }

    private val dataSourceFactory: GetCharactersDataSourceFactory by lazy {
        GetCharactersDataSourceFactory(
            viewModelScope,
            charactersUseCase,
            resource
        )
    }

    private val dataSource: LiveData<GetCharactersDataSource> by lazy {
        dataSourceFactory.getDataSource()
    }

    private val pagingConfig = PagedList.Config.Builder().run {
        setPageSize(20)
        setEnablePlaceholders(false)
        build()
    }

    private val characters: LiveData<PagedList<Character>>

    init {
        characters = LivePagedListBuilder<Int, Character>(dataSourceFactory, pagingConfig).build()
    }

    fun getCharacters(): LiveData<PagedList<Character>> = characters
    fun getResource(): LiveData<Resource<Int>> = resource

    fun invalidate() {
        dataSource.value?.invalidate()
    }
}