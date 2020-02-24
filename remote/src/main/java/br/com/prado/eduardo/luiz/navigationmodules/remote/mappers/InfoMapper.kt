package br.com.prado.eduardo.luiz.navigationmodules.remote.mappers

import br.com.prado.eduardo.luiz.navigationmodules.data.models.Info
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Origin
import br.com.prado.eduardo.luiz.navigationmodules.remote.dtos.InfoDTO
import br.com.prado.eduardo.luiz.navigationmodules.remote.dtos.OriginDTO

fun Info.toDto() = InfoDTO(count, next, pages, prev)

fun InfoDTO.toModel() = Info(count, next, pages, prev)
