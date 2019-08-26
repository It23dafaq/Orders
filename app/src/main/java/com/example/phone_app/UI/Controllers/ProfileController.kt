package com.example.phone_app.UI.Controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.phone_app.Data.Products
import com.example.phone_app.Data.tables
import com.example.phone_app.Network.ProductApi
import com.example.phone_app.Network.TablesApi


class ProfileControllerIml(
    private val TableService:TablesApi
): ProfileController {
    private val _downloadProduct = MutableLiveData<List<tables>>()
    override val downloadTable: LiveData<List<tables>>
        get() = _downloadProduct



    override suspend fun getTables() {
        val tables = TableService.getProducts().await()
        _downloadProduct.postValue(tables)
    }
}


interface ProfileController {
    val downloadTable: LiveData<List<tables>>
   suspend fun getTables()
}