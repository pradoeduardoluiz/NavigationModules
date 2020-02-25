package br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.location

import br.com.prado.eduardo.luiz.navigationmodules.common.NetworkErrorHandler
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Location
import br.com.prado.eduardo.luiz.navigationmodules.data.repositories.LocationRepository
import br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.UseCase

class GetLocations(
    private val repository: LocationRepository,
    networkErrorHandler: NetworkErrorHandler
) : UseCase<List<Location>, Int>(networkErrorHandler) {

    override suspend fun run(params: Int?): List<Location> {
        return repository.getLocations(params ?: 1)
    }

}