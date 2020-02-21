package br.com.prado.eduardo.luiz.navigationmodules.remote.util

import android.content.Context
import android.net.ConnectivityManager
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

val LOCALE_PT_BR = Locale("pt", "BR")

fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnectedOrConnecting
}

fun isInternetAccessible(): Boolean {
    try {
        val urlc = URL("http://www.google.com").openConnection() as HttpURLConnection
        urlc.setRequestProperty("User-Agent", "Test")
        urlc.setRequestProperty("Connection", "close")
        urlc.connectTimeout = 1500
        urlc.connect()
        return urlc.responseCode == 200
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return false
}