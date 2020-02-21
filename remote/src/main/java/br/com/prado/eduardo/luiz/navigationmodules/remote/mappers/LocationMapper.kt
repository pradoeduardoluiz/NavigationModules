package br.com.prado.eduardo.luiz.navigationmodules.remote.mappers

import br.com.prado.eduardo.luiz.navigationmodules.remote.dtos.LocationDTO
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Location

fun Location.toDto() = LocationDTO(id, type, dimension, name, residents, url, created)

fun LocationDTO.toModel() = Location(id, type, dimension, name, residents, url, created)
