package com.example.phone_app.network.repository


import androidx.lifecycle.LiveData
import com.example.phone_app.room.localized.ImperialProducts
import com.example.phone_app.room.localized.dao.ProductsDao

class ProductsRepository(private  val prodactdao:ProductsDao) {
    val allWords: LiveData<ImperialProducts> = prodactdao.getProducts()
}