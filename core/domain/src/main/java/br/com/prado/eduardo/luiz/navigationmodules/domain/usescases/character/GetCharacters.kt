package br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.character

import br.com.prado.eduardo.luiz.navigationmodules.common.NetworkErrorHandler
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import br.com.prado.eduardo.luiz.navigationmodules.data.models.CharacterResponse
import br.com.prado.eduardo.luiz.navigationmodules.data.repositories.CharacterRepository
import br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.UseCase

class GetCharacters(
    private val repository: CharacterRepository,
    networkErrorHandler: NetworkErrorHandler
) : UseCase<CharacterResponse, Int>(networkErrorHandler) {

    override suspend fun run(params: Int?): CharacterResponse {
        return repository.getCharacters(params ?: 1)
    }

}