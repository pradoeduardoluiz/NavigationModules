package br.com.prado.eduardo.luiz.navigationmodules.character.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.prado.eduardo.luiz.navigationmodules.character.R
import br.com.prado.eduardo.luiz.navigationmodules.character.interfaces.OnCharacterClickListener
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CharactersListAdapter(private val listener: OnCharacterClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val characters: MutableList<Character> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val requestOptions = RequestOptions().placeholder(R.drawable.ic_image_placeholder_24dp)

        holder as CharacterViewHolder

        holder.textView.text = characters[position].name
        Glide.with(holder.itemView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(characters[position].image)
            .into(holder.imageView)

    }

    fun setList(list: List<Character>) {
        characters.clear()
        characters.addAll(list)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val imageView: ImageView = itemView.findViewById(R.id.img_character)
        val textView: TextView = itemView.findViewById(R.id.txt_name)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.selectedItem(characters[adapterPosition])
        }
    }
}
