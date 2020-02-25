package br.com.prado.eduardo.luiz.navigationmodules.character.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.prado.eduardo.luiz.navigationmodules.character.R
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_character_details.*


class CharacterDetailsFragment : Fragment() {

    private val args: CharacterDetailsFragmentArgs by navArgs()
    private val character: Character by lazy { args.character }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_character_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildLayout()
    }

    private fun buildLayout() {

        txt_name.text = character.name
        txt_species.text = character.species
        txt_status.text = character.status
        txt_type.text = character.type
        txt_location.text = character.location.name
        txt_origin.text = character.origin.name

        val requestOptions = RequestOptions().placeholder(R.drawable.ic_image_placeholder_24dp)

        Glide.with(requireContext())
            .setDefaultRequestOptions(requestOptions)
            .load(character.image)
            .into(img_character)
    }

}
