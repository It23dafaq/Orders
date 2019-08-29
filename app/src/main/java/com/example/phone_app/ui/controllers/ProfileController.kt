package com.example.phone_app.ui.controllers

import com.example.phone_app.network.ProductApi


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