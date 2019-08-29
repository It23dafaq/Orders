package com.example.phone_app.network


import androidx.lifecycle.LiveData
import com.example.phone_app.model.Products

interface ProductNetworkDataSource {
    val downloadProduct: LiveData<List<Products>>

    suspend fun fetchCurrentWeather(

    )
}