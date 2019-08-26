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
    override suspend fun getUsers() {
        val products=   productService.getProducts().await()
        _downloadProduct.postValue(products)
    }
}

interface HomeController {
    val downloadProduct: LiveData<List<Products>>
    suspend fun getUsers()
}