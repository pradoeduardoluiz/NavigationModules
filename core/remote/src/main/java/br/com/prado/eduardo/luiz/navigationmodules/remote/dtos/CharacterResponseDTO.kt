package br.com.prado.eduardo.luiz.navigationmodules.remote.dtos

import com.google.gson.annotations.SerializedName

data class CharacterResponseDTO(
    @SerializedName("info")
    val info: InfoDTO,
    @SerializedName("results")
    val characters: List<CharacterDTO>
)