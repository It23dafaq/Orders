package com.example.phone_app.Data.Repository


import androidx.lifecycle.LiveData
import com.example.phone_app.Data.Products
import com.example.phone_app.Data.dataBase.Localized.ImperialProducts
import com.example.phone_app.Data.dataBase.Localized.dbAccessObject.ProductsDao

class ProductsRepository(private  val prodactdao:ProductsDao) {
    val allWords: LiveData<ImperialProducts> = prodactdao.getProducts()
}