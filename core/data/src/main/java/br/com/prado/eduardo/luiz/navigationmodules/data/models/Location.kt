package br.com.prado.eduardo.luiz.navigationmodules.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val id: Int,
    val type: String,
    val dimension: String,
    val name: String,
    val residents: List<String>,
    val url: String,
    val created: String
) : Parcelable