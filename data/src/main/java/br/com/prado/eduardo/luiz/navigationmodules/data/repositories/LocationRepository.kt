package br.com.prado.eduardo.luiz.navigationmodules.data.repositories

import br.com.prado.eduardo.luiz.navigationmodules.data.models.Location

interface LocationRepository {

    suspend fun getLocations(page: Int): List<Location>

}