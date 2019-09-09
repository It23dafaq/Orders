package com.example.phone_app.Network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.phone_app.CustomIOException.NoConnectivityException
import com.example.phone_app.Data.Orders
import com.example.phone_app.Data.Products

class OrderNetworkDatasourceImpl(
    private val apiOrders: OrderApi
) : OrderNetworkDatasource {
    private val _downloadProduct = MutableLiveData<List<Orders>>()
    override val downloadProduct: LiveData<List<Orders>>
        get() = _downloadProduct

    override suspend fun fetchOrders() {
        try{
            val fetchOrders = apiOrders.getOrders().await()
            _downloadProduct.postValue(fetchOrders)

        }catch (e: NoConnectivityException){
            Log.e("Connectivity","No Internet Connection",e)
        }
    }
}