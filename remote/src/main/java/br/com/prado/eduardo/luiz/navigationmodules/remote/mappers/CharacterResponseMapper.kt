package br.com.prado.eduardo.luiz.navigationmodules.remote.mappers

import br.com.prado.eduardo.luiz.navigationmodules.data.models.CharacterResponse
import br.com.prado.eduardo.luiz.navigationmodules.remote.dtos.CharacterResponseDTO

fun CharacterResponse.toDto() = CharacterResponseDTO(
    info.toDto(),
    characters.map { it.toDto() }

)

fun CharacterResponseDTO.toModel() = CharacterResponse(
    info.toModel(),
    characters.map { it.toModel() }
)