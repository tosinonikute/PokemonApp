package com.pokemon.util

import android.Manifest
import okhttp3.Interceptor
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasActiveNetworkConnection()) {
            throw NoInternetException("No active internet connection available. Please check your network settings.")
        }
        return chain.proceed(chain.request())
    }

    /**
     * Determines if the device has an active network connection with internet capability.
     * Uses different approaches based on API level for backward compatibility.
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private fun hasActiveNetworkConnection(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            ?: return false

        return checkNetworkConnection(connectivityManager)
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private fun checkNetworkConnection(connectivityManager: ConnectivityManager): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}
