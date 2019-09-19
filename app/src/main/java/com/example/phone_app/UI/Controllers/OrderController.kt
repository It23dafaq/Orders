package com.example.phone_app.UI.Controllers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.phone_app.CustomIOException.NoConnectivityException
import com.example.phone_app.Data.Orders
import com.example.phone_app.Network.OrderApi
import com.example.phone_app.Network.OrderNetworkDatasource


class OrderControllerImpl(
    private val apiOrders: OrderApi
) : OrderController {
    private val _downloadProduct = MutableLiveData<List<Orders>>()
    override val downloadProduct: LiveData<List<Orders>>
        get() = _downloadProduct

    override suspend fun fetchOrders(filter: String) {
               if(filter.equals("Generic")){
                   val fetchOrders = apiOrders.getOrders().await()
                   _downloadProduct.postValue(fetchOrders)
               }else{

                   val fetchOrders = apiOrders.getDaily().await()
                   _downloadProduct.postValue(fetchOrders)
               }


    }
}




interface OrderController {
    val downloadProduct: LiveData<List<Orders>>

    suspend fun fetchOrders(filter:String)
}