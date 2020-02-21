package br.com.prado.eduardo.luiz.navigationmodules

import android.app.Application
import br.com.prado.eduardo.luiz.navigationmodules.character.di.characterModule
import br.com.prado.eduardo.luiz.navigationmodules.location.di.locationModule
import br.com.prado.eduardo.luiz.navigationmodules.domain.di.domainModule
import br.com.prado.eduardo.luiz.navigationmodules.remote.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class AppApplication : Application() {

    private val features = listOf(characterModule, locationModule)
    private val archModules = listOf(domainModule, remoteModule)

    override fun onCreate() {
        super.onCreate()

        var modules: MutableList<Module> = mutableListOf()
        modules.addAll(features)
        modules.addAll(archModules)

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(modules)
        }
    }
}