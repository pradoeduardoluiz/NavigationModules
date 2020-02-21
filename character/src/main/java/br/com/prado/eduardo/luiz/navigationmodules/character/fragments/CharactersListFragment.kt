package br.com.prado.eduardo.luiz.navigationmodules.character.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.prado.eduardo.luiz.navigationmodules.character.R
import br.com.prado.eduardo.luiz.navigationmodules.character.adapters.CharactersListAdapter
import br.com.prado.eduardo.luiz.navigationmodules.character.interfaces.OnCharacterClickListener
import br.com.prado.eduardo.luiz.navigationmodules.character.viewmodels.CharactersListViewModel
import br.com.prado.eduardo.luiz.navigationmodules.common.Resource
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import kotlinx.android.synthetic.main.fragment_characters_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersListFragment : Fragment(), OnCharacterClickListener {

    private val viewModel: CharactersListViewModel by viewModel()
    private val adapter: CharactersListAdapter by lazy {
        CharactersListAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_characters_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        initRecyclerView()

        viewModel.fetchCharacters()
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            visibility = View.VISIBLE
            adapter = this@CharactersListFragment.adapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (!recyclerView.canScrollVertically(1)) {
                        //mRecipeListViewModel.searchNextPage()
                    }
                }
            })
        }
    }

    private fun subscribeObservers() {
        viewModel.getCharacters().observe(viewLifecycleOwner, Observer {
            when (it) {
                Resource.Loading -> progress_bar.visibility = View.VISIBLE
                Resource.Complete -> progress_bar.visibility = View.GONE
                is Resource.Success -> {
                    setList(it.data)
                }
                is Resource.Error.Api -> {
                    Toast.makeText(requireContext(), "Ocorreu um Erro", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error.Connection -> {
                    Toast.makeText(requireContext(), "Ocorreu um Erro", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setList(list: List<Character>) {
        adapter.setList(list)
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
