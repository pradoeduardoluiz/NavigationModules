package br.com.prado.eduardo.luiz.navigationmodules.remote.services

import br.com.prado.eduardo.luiz.navigationmodules.remote.dtos.LocationResponseDTO
import retrofit2.http.GET

interface LocationAPI {

    // GET
    @GET("location")
    suspend fun getLocations(
    ): LocationResponseDTO

}