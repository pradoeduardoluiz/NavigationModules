package br.com.prado.eduardo.luiz.navigationmodules.remote.mappers

import br.com.prado.eduardo.luiz.navigationmodules.data.models.Origin
import br.com.prado.eduardo.luiz.navigationmodules.remote.dtos.OriginDTO

fun Origin.toDto() = OriginDTO(name, url)

fun OriginDTO.toModel() = Origin(name, url)
