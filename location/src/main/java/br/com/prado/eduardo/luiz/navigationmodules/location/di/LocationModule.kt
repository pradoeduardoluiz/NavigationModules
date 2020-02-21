package br.com.prado.eduardo.luiz.navigationmodules.location.di

import br.com.prado.eduardo.luiz.navigationmodules.location.fragments.LocationListFragment
import br.com.prado.eduardo.luiz.navigationmodules.location.viewmodels.LocationListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val locationModule: Module = module {

    factory { LocationListFragment() }
    viewModel {
        LocationListViewModel(get())
    }
}
