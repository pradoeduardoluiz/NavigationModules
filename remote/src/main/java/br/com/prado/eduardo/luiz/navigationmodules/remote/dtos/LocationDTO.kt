package br.com.prado.eduardo.luiz.navigationmodules.remote.dtos

import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)