package br.com.prado.eduardo.luiz.navigationmodules.character.adapters

import br.com.prado.eduardo.luiz.navigationmodules.character.holders.CharacterViewHolder_
import br.com.prado.eduardo.luiz.navigationmodules.character.holders.LoadingViewHolder_
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController

class CharactersListController : PagedListEpoxyController<Character>() {

    var isLoading = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    override fun buildItemModel(currentPosition: Int, item: Character?): EpoxyModel<*> {
        item?.let {
            return CharacterViewHolder_().apply {
                id(it.id)
                name(it.name)
                imageUrl(it.image)
            }
        } ?: run {
            return LoadingViewHolder_().apply {
                id("loading")
            }
        }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        if (isLoading) {
            super.addModels(
                models.plus(
                    //Error View Model
                    LoadingViewHolder_()
                        .id("loading")
                ).distinct()
            )
        } else {
            super.addModels(models.distinct())
        }
    }
}