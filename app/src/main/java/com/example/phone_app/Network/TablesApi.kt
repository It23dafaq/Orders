package com.example.phone_app.Network

import com.example.phone_app.Data.Products
import com.example.phone_app.Data.tables
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URLtables = "https://rectifiable-merchan.000webhostapp.com/"

interface TablesApi{

    @GET("showTables.php")
    fun getProducts() : Deferred<List<tables>>



    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : TablesApi {
            val okHttpClient = OkHttpClient.Builder().addInterceptor(connectivityInterceptor).build()

            return Retrofit.Builder().client(okHttpClient)
                .baseUrl(BASE_URLtables).addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TablesApi::class.java)


        }
    }
}