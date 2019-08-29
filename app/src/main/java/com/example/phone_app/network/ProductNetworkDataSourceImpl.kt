package com.example.phone_app.network


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.phone_app.utils.NoConnectivityException
import com.example.phone_app.model.Products

class ProductNetworkDataSourceImpl(
    private val apiProduct: ProductApi
) : ProductNetworkDataSource {

    private val _downloadProduct = MutableLiveData<List<Products>>()
    override val downloadProduct: LiveData<List<Products>>
        get() = _downloadProduct

    override suspend fun fetchCurrentWeather() {
      try{
            val fetchproducts = apiProduct.getProducts().await()
          _downloadProduct.postValue(fetchproducts)

      }catch (e: NoConnectivityException){
          Log.e("Connectivity","No Internet Connection",e)
      }
    }
}