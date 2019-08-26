package com.example.phone_app.Network


import androidx.lifecycle.LiveData
import com.example.phone_app.Data.Products

interface ProductNetworkDataSource {
    val downloadProduct: LiveData<List<Products>>

    suspend fun fetchCurrentWeather(

    )
}