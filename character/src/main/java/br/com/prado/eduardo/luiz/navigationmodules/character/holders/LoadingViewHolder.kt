package br.com.prado.eduardo.luiz.navigationmodules.character.holders

import android.view.View
import android.widget.ProgressBar
import br.com.prado.eduardo.luiz.navigationmodules.character.R
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.list_item_loading.view.*

@EpoxyModelClass
abstract class LoadingViewHolder : EpoxyModelWithHolder<LoadingHolder>() {
    override fun getDefaultLayout() = R.layout.list_item_loading

    override fun bind(holder: LoadingHolder) {
        holder.apply {
            progressBar.visibility = View.VISIBLE
        }
    }
}

class LoadingHolder : EpoxyHolder() {
    lateinit var progressBar: ProgressBar

    override fun bindView(itemView: View) {
        progressBar = itemView.progress_bar
    }
}