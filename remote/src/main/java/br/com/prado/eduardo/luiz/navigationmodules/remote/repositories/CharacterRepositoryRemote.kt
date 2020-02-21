package br.com.prado.eduardo.luiz.navigationmodules.remote.repositories

import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import br.com.prado.eduardo.luiz.navigationmodules.data.repositories.CharacterRepository
import br.com.prado.eduardo.luiz.navigationmodules.remote.mappers.toModel
import br.com.prado.eduardo.luiz.navigationmodules.remote.services.CharacterAPI


class CharacterRepositoryRemote(private val api: CharacterAPI) : CharacterRepository {

    override suspend fun getCharacters(page: Int): List<Character> {
        return api.getCharacters().characters.map { it.toModel() }
    }

}