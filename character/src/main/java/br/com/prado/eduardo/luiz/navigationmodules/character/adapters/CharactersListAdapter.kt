package br.com.prado.eduardo.luiz.navigationmodules.character.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.prado.eduardo.luiz.navigationmodules.character.R
import br.com.prado.eduardo.luiz.navigationmodules.character.interfaces.OnCharacterClickListener
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CharactersListAdapter(private val listener: OnCharacterClickListener) :
    PagedListAdapter<Character, CharactersListAdapter.CharacterViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    inner class CharacterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val imageView: ImageView = itemView.findViewById(R.id.img_character)
        private val textView: TextView = itemView.findViewById(R.id.txt_name)

        init {
            itemView.setOnClickListener(this)
        }

        fun bindPost(character: Character) {
            with(character) {
                val requestOptions =
                    RequestOptions().placeholder(R.drawable.ic_image_placeholder_24dp)
                textView.text = name
                Glide.with(itemView.context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(image)
                    .into(imageView)
            }
        }

        override fun onClick(v: View?) {
            //listener.selectedItem(characters[adapterPosition])
        }
    }
}
