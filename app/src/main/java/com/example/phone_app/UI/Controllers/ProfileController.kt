package com.example.phone_app.UI.Controllers

import com.example.phone_app.Network.ProductApi


class ProfileControllerIml(
    private val productService: ProductApi
): ProfileController {
    override suspend fun getUsers() {
        productService.getProducts().await()
    }
}


interface ProfileController {
   suspend fun getUsers()
}