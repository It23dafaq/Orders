package com.example.phone_app.UI.Controllers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.phone_app.CustomIOException.NoConnectivityException
import com.example.phone_app.Data.Orders
import com.example.phone_app.Data.OrdersByname
import com.example.phone_app.Network.OrderApi
import com.example.phone_app.Network.OrderNetworkDatasource


class OrderControllerImpl(
    private val apiOrders: OrderApi
) : OrderController {
    override suspend fun fetchOrdersByname(filter: String, name: String) {
        if(filter.equals("Today")){
            val fetchOrders = apiOrders.getDailyByname(name).await()
            _downloadProductbyname.postValue(fetchOrders)
        }else if (filter.equals("Week")){
            val fetchOrders = apiOrders.getWeekByname(name).await()
            _downloadProductbyname.postValue(fetchOrders)
        }else if (filter.equals("Month")){
            val fetchOrders = apiOrders.getMonthByname(name).await()
            _downloadProductbyname.postValue(fetchOrders)
        }else{
            val fetchOrders = apiOrders.getYearByname(name).await()
            _downloadProductbyname.postValue(fetchOrders)
        }

    }
    private val _downloadProductbyname = MutableLiveData<List<OrdersByname>>()
    override val downloadProductbyname: LiveData<List<OrdersByname>>
        get() = _downloadProductbyname
    private val _downloadProduct = MutableLiveData<List<Orders>>()
    override val downloadProduct: LiveData<List<Orders>>
        get() = _downloadProduct

    override suspend fun fetchOrders(filter: String) {
               if(filter.equals("Today")){
                   val fetchOrders = apiOrders.getDaily().await()
                   _downloadProduct.postValue(fetchOrders)
               }else if(filter.equals("Week")){

                   val fetchOrders = apiOrders.getWeek().await()
                   _downloadProduct.postValue(fetchOrders)
               }else if(filter.equals("Month")){
                   val fetchOrders = apiOrders.getMonth().await()
                   _downloadProduct.postValue(fetchOrders)
               }else if(filter.equals("Year")){
                   val fetchOrders = apiOrders.getYear().await()
                   _downloadProduct.postValue(fetchOrders)
               }else{
                   val fetchOrders = apiOrders.getOrders().await()
                   _downloadProduct.postValue(fetchOrders)
               }


    }
}




interface OrderController {
    val downloadProduct: LiveData<List<Orders>>
    val downloadProductbyname: LiveData<List<OrdersByname>>

    suspend fun fetchOrders(filter:String)
    suspend fun fetchOrdersByname(filter: String,name:String)
}