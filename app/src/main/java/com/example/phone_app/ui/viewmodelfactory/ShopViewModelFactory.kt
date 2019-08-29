package com.example.phone_app.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phone_app.ui.viewmodels.ShopViewModel
import com.example.phone_app.ui.controllers.ShopController

class ShopViewModelFactory(private val shopController: ShopController): ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShopViewModel(shopController) as T
    }

}
