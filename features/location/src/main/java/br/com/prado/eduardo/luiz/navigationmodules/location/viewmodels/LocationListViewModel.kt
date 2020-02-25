package br.com.prado.eduardo.luiz.navigationmodules.location.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.prado.eduardo.luiz.navigationmodules.common.NetworkError
import br.com.prado.eduardo.luiz.navigationmodules.common.Resource
import br.com.prado.eduardo.luiz.navigationmodules.data.models.Location
import br.com.prado.eduardo.luiz.navigationmodules.domain.UseCaseResponse
import br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.location.GetLocations

class LocationListViewModel(private val locationsUseCase: GetLocations) : ViewModel() {

    private val locations: MutableLiveData<Resource<List<Location>>> by lazy {
        MutableLiveData<Resource<List<Location>>>()
    }

    fun getLocations(): LiveData<Resource<List<Location>>> = locations

    fun fetchLocations() {
        locations.value = Resource.Loading

        locationsUseCase.invoke(
            scope = viewModelScope,
            response = object : UseCaseResponse<List<Location>> {

                override fun onComplete() {
                    locations.value = Resource.Complete
                }

                override fun onSuccess(result: List<Location>) {
                    locations.value = Resource.Success(result)
                }

                override fun onFailure(error: NetworkError) {
                    locations.value = Resource.Error.Api(error)
                }

                override fun onError(exception: Exception) {
                    locations.value = Resource.Error.Connection(exception)
                }
            }
        )
    }


}