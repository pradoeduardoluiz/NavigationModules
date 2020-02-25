package br.com.prado.eduardo.luiz.navigationmodules.character.adapters

import androidx.recyclerview.widget.DiffUtil
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character

class DiffUtilCallBack : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
//                && oldItem.score == newItem.score
//                && oldItem.commentCount == newItem.commentCount
    }

}