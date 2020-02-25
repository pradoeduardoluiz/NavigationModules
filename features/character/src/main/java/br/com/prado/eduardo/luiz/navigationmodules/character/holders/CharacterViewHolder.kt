package br.com.prado.eduardo.luiz.navigationmodules.character.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import br.com.prado.eduardo.luiz.navigationmodules.character.R
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.list_item_character.view.*

@EpoxyModelClass
abstract class CharacterViewHolder : EpoxyModelWithHolder<CharacterHolder>() {
    override fun getDefaultLayout() = R.layout.list_item_character

    @EpoxyAttribute
    lateinit var name: String
    @EpoxyAttribute
    lateinit var imageUrl: String

    override fun bind(holder: CharacterHolder) {
        holder.apply {
            tvName.text = name
            val requestOptions =
                RequestOptions().placeholder(R.drawable.ic_image_placeholder_24dp)
            Glide.with(imgCharacter.context)
                .setDefaultRequestOptions(requestOptions)
                .load(imageUrl)
                .into(imgCharacter)
        }
    }
}

class CharacterHolder : EpoxyHolder() {
    lateinit var tvName: TextView
    lateinit var imgCharacter: ImageView

    override fun bindView(itemView: View) {
        tvName = itemView.txt_name
        imgCharacter = itemView.img_character
    }
}