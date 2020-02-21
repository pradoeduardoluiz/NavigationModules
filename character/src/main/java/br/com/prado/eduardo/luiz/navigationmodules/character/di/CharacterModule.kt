package br.com.prado.eduardo.luiz.navigationmodules.character.di

import br.com.prado.eduardo.luiz.navigationmodules.character.fragments.CharactersListFragment
import br.com.prado.eduardo.luiz.navigationmodules.character.viewmodels.CharactersListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val characterModule: Module = module {

    factory { CharactersListFragment() }
    viewModel {
        CharactersListViewModel(get())
    }
}
