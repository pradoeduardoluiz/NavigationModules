package br.com.prado.eduardo.luiz.navigationmodules.location.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.prado.eduardo.luiz.navigationmodules.common.Resource
import br.com.prado.eduardo.luiz.navigationmodules.location.R
import br.com.prado.eduardo.luiz.navigationmodules.location.controllers.LocationListController
import br.com.prado.eduardo.luiz.navigationmodules.location.viewmodels.LocationListViewModel
import kotlinx.android.synthetic.main.fragment_location_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class LocationListFragment : Fragment() {

    private val viewModel: LocationListViewModel by viewModel()

    private val controller: LocationListController by lazy {
        LocationListController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_location_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservables()

        initRecyclerView()

        viewModel.fetchLocations()
    }

    private fun subscribeObservables() {
        viewModel.getLocations().observe(viewLifecycleOwner, Observer {
            when (it) {
                Resource.Loading -> progress_bar.visibility = View.VISIBLE
                Resource.Complete -> progress_bar.visibility = View.GONE
                is Resource.Success -> {
                    controller.setData(it.data, false)
                    //throw PixelwolfException("Pixelwolf Exception")
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

    private fun initRecyclerView() {
        recycler_view.adapter = controller.adapter
    }

}

class PixelwolfException(m: String) : Exception(m)
