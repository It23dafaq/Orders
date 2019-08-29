package com.example.phone_app.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.phone_app.utils.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {

    private  val appContext=context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
       if(!isOnline())
           throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean{
        val connectivityManagert=appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as
                ConnectivityManager
        val networkInfo = connectivityManagert.activeNetworkInfo
        return networkInfo!=null && networkInfo.isConnected
    }
}