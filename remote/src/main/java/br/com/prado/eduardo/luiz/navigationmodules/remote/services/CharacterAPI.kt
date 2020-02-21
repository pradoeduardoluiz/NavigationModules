package br.com.prado.eduardo.luiz.navigationmodules.remote.services

import br.com.prado.eduardo.luiz.navigationmodules.remote.dtos.CharacterResponseDTO
import retrofit2.http.GET

interface CharacterAPI {

    // GET
    @GET("character")
    suspend fun getCharacters(
    ): CharacterResponseDTO

}