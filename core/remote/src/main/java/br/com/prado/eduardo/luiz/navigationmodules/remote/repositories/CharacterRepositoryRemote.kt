package br.com.prado.eduardo.luiz.navigationmodules.remote.repositories

import br.com.prado.eduardo.luiz.navigationmodules.data.models.CharacterResponse
import br.com.prado.eduardo.luiz.navigationmodules.data.repositories.CharacterRepository
import br.com.prado.eduardo.luiz.navigationmodules.remote.mappers.toModel
import br.com.prado.eduardo.luiz.navigationmodules.remote.services.CharacterAPI


class CharacterRepositoryRemote(private val api: CharacterAPI) : CharacterRepository {

    override suspend fun getCharacters(page: Int): CharacterResponse {
        return api.getCharacters(page).toModel()
    }

}