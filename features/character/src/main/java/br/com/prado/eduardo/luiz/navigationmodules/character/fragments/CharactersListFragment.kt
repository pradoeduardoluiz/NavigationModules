package br.com.prado.eduardo.luiz.navigationmodules.character.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.prado.eduardo.luiz.navigationmodules.character.R
import br.com.prado.eduardo.luiz.navigationmodules.character.adapters.CharactersListController
import br.com.prado.eduardo.luiz.navigationmodules.character.interfaces.OnCharacterClickListener
import br.com.prado.eduardo.luiz.navigationmodules.character.viewmodels.CharactersListViewModel
import br.com.prado.eduardo.luiz.navigationmodules.common.Resource
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Character
import kotlinx.android.synthetic.main.fragment_characters_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CharactersListFragment : Fragment(), OnCharacterClickListener {

    private val viewModel: CharactersListViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(R.id.nav_characters)
    })

    private val controller: CharactersListController by lazy {
        CharactersListController()
    }

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_characters_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        subscribeObservers()
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            adapter = controller.adapter
            visibility = View.VISIBLE
        }
    }

    private fun subscribeObservers() {
        viewModel.getCharacters().observe(requireActivity(), Observer {
            controller.submitList(it)
            if (viewModel.getCurrentPosition() != -1) {
                recycler_view.layoutManager?.scrollToPosition(viewModel.getCurrentPosition())
                viewModel.setCurrentPosition(-1)
            }
        })

        viewModel.getResource().observe(requireActivity(), Observer {
            when (it) {
                Resource.Loading -> {
                    controller.isLoading = true
                }
                is Resource.Success -> {
                    controller.isLoading = false
                }
                is Resource.Error.Api -> {
                }
                is Resource.Error.Connection -> {

                }
            }
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

    override fun onPause() {
        val linearLayoutManager = (recycler_view.layoutManager as LinearLayoutManager)
        val positionIndex =
            if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() != -1) {
                linearLayoutManager.findFirstCompletelyVisibleItemPosition()
            } else {
                linearLayoutManager.findFirstVisibleItemPosition()
            }
        viewModel.setCurrentPosition(positionIndex)
        Log.d(TAG, "[onPause]: $positionIndex")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d(TAG, "[onDestroy]: ")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG, "[onDetach]: ")
        super.onDetach()
    }

    companion object {
        private const val TAG = "CharactersListFragment"
    }

}
