package br.com.prado.eduardo.luiz.navigationmodules.remote.di

import android.content.Context
import br.com.prado.eduardo.luiz.navigationmodules.data.repositories.CharacterRepository
import br.com.prado.eduardo.luiz.navigationmodules.remote.Network
import br.com.prado.eduardo.luiz.navigationmodules.remote.repositories.CharacterRepositoryRemote
import br.com.prado.eduardo.luiz.navigationmodules.remote.services.CharacterAPI
import br.com.prado.eduardo.luiz.navigationmodules.remote.util.RemoteConstants
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteModule: Module = module {
    single {
        createRetrofit(
            baseUrl = RemoteConstants.API_BASE_URL,
            context = get()
        )
    }
    single { get<Retrofit>().create(CharacterAPI::class.java) }

    factory<CharacterRepository> { CharacterRepositoryRemote(api = get()) }

}

private fun createRetrofit(baseUrl: String, context: Context): Retrofit {
    return Network.createRetrofit(baseUrl, context)
}