package com.example.phone_app.Network

import com.example.phone_app.Data.Products
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
//https://rectifiable-merchan.000webhostapp.com/ShowGin.php

//const val BASE_URL = "https://rectifiable-merchan.000webhostapp.com/e_com/"
const val BASE_URL = "https://rectifiable-merchan.000webhostapp.com/"

interface ProductApi {

    @GET("present_json_array.php")
    fun getProducts() : Deferred <List<Products>>

    @GET("ShowVodka.php")
    fun getVodka() : Deferred <List<Products>>

    @GET("ShowGin.php")
    fun getGin() : Deferred <List<Products>>

    @GET("ShowRum.php")
    fun getRum() : Deferred <List<Products>>

    @GET("ShowTequila.php")
    fun getTequila() : Deferred <List<Products>>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : ProductApi {
           val okHttpClient = OkHttpClient.Builder().addInterceptor(connectivityInterceptor).build()

            return Retrofit.Builder().client(okHttpClient)
                .baseUrl(BASE_URL).addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductApi::class.java)


        }
    }
}