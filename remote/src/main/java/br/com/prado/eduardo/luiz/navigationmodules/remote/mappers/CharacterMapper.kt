package br.com.prado.eduardo.luiz.navigationmodules.remote.mappers

import br.com.prado.eduardo.luiz.navigationmodules.remote.dtos.CharacterDTO
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character

fun Character.toDto() = CharacterDTO(
    created,
    episode,
    gender,
    id,
    image,
    location.toDto(),
    name,
    origin.toDto(),
    species,
    status,
    type,
    url
)

fun CharacterDTO.toModel() = Character(
    created,
    episode,
    gender,
    id,
    image,
    location.toModel(),
    name,
    origin.toModel(),
    species,
    status,
    type,
    url
)