package br.com.prado.eduardo.luiz.navigationmodules.character.interfaces

import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character

interface OnCharacterClickListener {

    fun selectedItem(character: Character)

}