package br.com.prado.eduardo.luiz.navigationmodules.remote.repositories

import br.com.prado.eduardo.luiz.navigationmodules.data.models.Location
import br.com.prado.eduardo.luiz.navigationmodules.data.repositories.LocationRepository
import br.com.prado.eduardo.luiz.navigationmodules.remote.mappers.toModel
import br.com.prado.eduardo.luiz.navigationmodules.remote.services.LocationAPI

class LocationRepositoryRemote(private val api: LocationAPI) : LocationRepository {

    override suspend fun getLocations(page: Int): List<Location> {
        return api.getLocations().locations.map { it.toModel() }
    }
}