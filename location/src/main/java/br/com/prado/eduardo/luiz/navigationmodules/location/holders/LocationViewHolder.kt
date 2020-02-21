package br.com.prado.eduardo.luiz.navigationmodules.location.holders

import android.view.View
import android.widget.TextView
import br.com.prado.eduardo.luiz.navigationmodules.location.R
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.list_item_location.view.*

@EpoxyModelClass
abstract class LocationViewHolder : EpoxyModelWithHolder<Holder>() {
    override fun getDefaultLayout() = R.layout.list_item_location

    @EpoxyAttribute
    lateinit var name: String
    @EpoxyAttribute
    lateinit var dimension: String
    @EpoxyAttribute
    lateinit var type: String

    override fun bind(holder: Holder) {
        holder.apply {
            tvName.text = name
            tvDimension.text = dimension
            tvType.text = type
        }
    }
}

class Holder : EpoxyHolder() {
    lateinit var tvName: TextView
    lateinit var tvDimension: TextView
    lateinit var tvType: TextView

    override fun bindView(itemView: View) {
        tvName = itemView.txt_name
        tvDimension = itemView.txt_dimension
        tvType = itemView.txt_dimension
    }
}