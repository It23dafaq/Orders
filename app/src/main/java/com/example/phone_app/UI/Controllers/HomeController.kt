package com.example.phone_app.UI.Controllers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.phone_app.Data.Products
import com.example.phone_app.Network.ProductApi
import retrofit2.await


class HomeControllerIml(
    private val productService: ProductApi
): HomeController {
    override suspend fun inserOrders(
        quantity: String,
        username: String,
        drinkname: String,
        price: Double,
        comments: String
    ) {
       val products= productService.insertOrders(quantity,username,drinkname,price,comments)
       // Log.d("response",products.body().toString())

    }

    private val _downloadProduct = MutableLiveData<List<Products>>()
    override val downloadProduct: LiveData<List<Products>>
        get() =_downloadProduct
    override suspend fun getUsers(string: String) {
        if (string.equals("Vodka")) {
            val products = productService.getVodka().await()
            _downloadProduct.postValue(products)
        } else if (string.equals("Gin")) {
            val products = productService.getGin().await()
            _downloadProduct.postValue(products)
        } else if (string.equals("Rum")) {
            val products = productService.getRum().await()
            _downloadProduct.postValue(products)
        }else if (string.equals("Tequila")){
            val products = productService.getTequila().await()
            _downloadProduct.postValue(products)
        }else if(string.equals("Others")){
            val products = productService.getOthers().await()
            _downloadProduct.postValue(products)
        }
    }
}

interface HomeController {
    val downloadProduct: LiveData<List<Products>>
    suspend fun getUsers(string: String)
    suspend fun inserOrders(quantity: String,username: String,drinkname: String,price: Double,comments: String)

}