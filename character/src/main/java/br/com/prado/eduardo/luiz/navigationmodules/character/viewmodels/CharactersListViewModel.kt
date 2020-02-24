package br.com.prado.eduardo.luiz.navigationmodules.character.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.character.GetCharacters
import br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.character.GetCharactersDataSource

class CharactersListViewModel(
    private val charactersUseCase: GetCharacters
) : ViewModel() {

    private val characters: LiveData<PagedList<Character>>

    init {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        characters = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Character> {

        val dataSourceFactory = object : DataSource.Factory<Int, Character>() {
            override fun create(): DataSource<Int, Character> {
                return GetCharactersDataSource(viewModelScope, charactersUseCase)
            }
        }
        return LivePagedListBuilder<Int, Character>(dataSourceFactory, config)
    }

    fun getCharacters(): LiveData<PagedList<Character>> = characters

//    fun fetchCharacters() {
//
//    }
}