package com.example.phone_app.UI.Controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.phone_app.Data.Products
import com.example.phone_app.Network.ProductApi


class HomeControllerIml(
    private val productService: ProductApi
): HomeController {
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
        }
    }
}

interface HomeController {
    val downloadProduct: LiveData<List<Products>>
    suspend fun getUsers(string: String)
}