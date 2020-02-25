package br.com.prado.eduardo.luiz.navigationmodules.location.controllers

import br.com.prado.eduardo.luiz.navigationmodules.data.models.Location
import br.com.prado.eduardo.luiz.navigationmodules.location.holders.locationViewHolder
import com.airbnb.epoxy.Typed2EpoxyController

class LocationListController : Typed2EpoxyController<List<Location>, Boolean>() {

    override fun buildModels(locations: List<Location>?, data2: Boolean?) {
        locations?.forEach { location ->
            locationViewHolder {
                id(location.id)
                name(location.name)
                dimension(location.dimension)
                type(location.type)
            }
        }
    }

}