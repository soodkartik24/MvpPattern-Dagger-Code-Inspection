package com.soodkartik.mvppattern.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.soodkartik.mvppattern.R
import com.soodkartik.mvppattern.application.TravelAppApplication
import okhttp3.Interceptor
import okhttp3.Response

class NetworkUtility(context: Context?) : Interceptor {

    private val applicationContext = context?.applicationContext

    @RequiresApi(Build.VERSION_CODES.M)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException(
                TravelAppApplication.getApplicationComponent()?.getApplicationInstance()
                    ?.getString(R.string.check_internet_connection)
            )
        try {
            return chain.proceed(chain.request())
        } catch (exception: Exception) {
            throw NoServerFoundException(exception.message)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }
}