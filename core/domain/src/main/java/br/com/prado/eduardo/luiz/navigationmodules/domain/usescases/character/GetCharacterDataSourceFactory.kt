package br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import br.com.prado.eduardo.luiz.navigationmodules.common.Resource
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import kotlinx.coroutines.CoroutineScope

class GetCharactersDataSourceFactory(
    private val scope: CoroutineScope,
    private val charactersUseCase: GetCharacters,
    private val resource: MutableLiveData<Resource<Int>>
) :
    DataSource.Factory<Int, Character>() {

    private val dataSourceLiveData: MutableLiveData<GetCharactersDataSource> by lazy {
        MutableLiveData<GetCharactersDataSource>()
    }

    private lateinit var dataSource: GetCharactersDataSource

    override fun create(): DataSource<Int, Character> {
        dataSource = GetCharactersDataSource(
            scope,
            charactersUseCase,
            resource
        )
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

    fun getDataSource(): LiveData<GetCharactersDataSource> = dataSourceLiveData
}