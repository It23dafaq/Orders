package com.example.phone_app.UI.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phone_app.UI.ViewModels.ShopViewModel
import com.example.phone_app.UI.Controllers.ShopController

class ShopViewModelFactory(private val shopController: ShopController): ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShopViewModel(shopController) as T
    }

}
