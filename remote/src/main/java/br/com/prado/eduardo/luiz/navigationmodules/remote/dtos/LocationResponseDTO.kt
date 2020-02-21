package br.com.prado.eduardo.luiz.navigationmodules.remote.dtos

import com.google.gson.annotations.SerializedName

data class LocationResponseDTO(
    @SerializedName("info")
    val info: InfoDTO,
    @SerializedName("results")
    val locations: List<LocationDTO>
)