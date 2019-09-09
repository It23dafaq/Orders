package com.example.phone_app.Network

import androidx.lifecycle.LiveData
import com.example.phone_app.Data.Orders
import com.example.phone_app.Data.Products

interface OrderNetworkDatasource {
    val downloadProduct: LiveData<List<Orders>>

    suspend fun fetchOrders(

    )
}