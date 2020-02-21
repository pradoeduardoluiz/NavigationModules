package br.com.prado.eduardo.luiz.navigationmodules.data.repositories

import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character

interface CharacterRepository {

    suspend fun getCharacters(page: Int): List<Character>

}