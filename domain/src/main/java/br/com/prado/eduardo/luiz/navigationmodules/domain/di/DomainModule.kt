package br.com.prado.eduardo.luiz.navigationmodules.domain.di

import br.com.prado.eduardo.luiz.navigationmodules.common.NetworkErrorHandler
import br.com.prado.eduardo.luiz.navigationmodules.domain.usescases.character.GetCharacters
import org.koin.core.module.Module
import org.koin.dsl.module

val domainModule: Module = module {

    single { NetworkErrorHandler() }

    factory { GetCharacters(repository = get(), networkErrorHandler = get()) }
}