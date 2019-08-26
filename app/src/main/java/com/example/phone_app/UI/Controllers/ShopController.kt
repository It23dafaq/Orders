package com.example.phone_app.UI.Controllers

import com.example.phone_app.Network.ProductApi


class ShopControllerImpl( private val productService: ProductApi): ShopController {
    override suspend fun getUsers() {

    }

}


interface ShopController {
suspend fun getUsers()
}