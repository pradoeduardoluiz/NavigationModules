package br.com.prado.eduardo.luiz.navigationmodules.remote

import android.content.Context
import br.com.prado.eduardo.luiz.navigationmodules.remote.util.isInternetAccessible
import br.com.prado.eduardo.luiz.navigationmodules.remote.util.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object Network {

    private val LOGGING_LEVEL: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY

    fun createLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = LOGGING_LEVEL
    }

    private fun createOkHttpClient(context: Context) = OkHttpClient.Builder()
        .addInterceptor(createLoggingInterceptor())
        .addInterceptor(createConnectivityAwareInterceptor(context))
        .build()

    fun createRetrofit(
        url: String,
        context: Context
    ): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(createOkHttpClient(context))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun createConnectivityAwareInterceptor(context: Context): Interceptor {
        return Interceptor { chain ->
            if (!context.isNetworkAvailable() && !isInternetAccessible()) throw IOException()
            else chain.proceed(chain.request())
        }
    }
}