package br.com.prado.eduardo.luiz.navigationmodules.remote.mappers

import br.com.prado.eduardo.luiz.navigationmodules.remote.dtos.LocationDTO
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Location

fun Location.toDto() = LocationDTO(name, url)

fun LocationDTO.toModel() = Location(name, url)
