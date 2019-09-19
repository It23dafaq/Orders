package com.example.phone_app.UI.ViewModelFactory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phone_app.Network.OrderNetworkDatasource
import com.example.phone_app.UI.ViewModels.HomeViewModel
import com.example.phone_app.UI.Controllers.HomeController
import com.example.phone_app.UI.Controllers.OrderController

class HomeViewModelFactory(private val homeController: HomeController,private val orderController: OrderController): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(homeController, orderController ) as T
    }

}