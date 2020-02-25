package br.com.prado.eduardo.luiz.navigationmodules.character.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.prado.eduardo.luiz.navigationmodules.character.R
import br.com.prado.eduardo.luiz.navigationmodules.character.adapters.CharactersListController
import br.com.prado.eduardo.luiz.navigationmodules.character.interfaces.OnCharacterClickListener
import br.com.prado.eduardo.luiz.navigationmodules.character.viewmodels.CharactersListViewModel
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import kotlinx.android.synthetic.main.fragment_characters_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersListFragment : Fragment(), OnCharacterClickListener {

    private val viewModel: CharactersListViewModel by viewModel()
    private val controller: CharactersListController by lazy {
        CharactersListController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_characters_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            visibility = View.VISIBLE
            adapter = controller.adapter
        }
    }

    private fun subscribeObservers() {
        viewModel.getCharacters().observe(requireActivity(), Observer {
            controller.submitList(it)
        })

        viewModel.isLoading().observe(requireActivity(), Observer {
            controller.isLoading = it
        })
    }

    override fun selectedItem(character: Character) {
        findNavController().navigate(
            CharactersListFragmentDirections
                .actionCharactersListFragmentToCharacterDetailsFragment(
                    character = character
                )
        )
    }
}