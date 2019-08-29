package com.example.phone_app.ui.controllers

import com.example.phone_app.network.ProductApi


class ShopControllerImpl( private val productService: ProductApi): ShopController {
    override suspend fun getUsers() {

    }

}


interface ShopController {
suspend fun getUsers()
}