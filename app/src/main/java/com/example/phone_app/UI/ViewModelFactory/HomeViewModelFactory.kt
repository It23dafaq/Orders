package com.example.phone_app.UI.ViewModelFactory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phone_app.UI.ViewModels.HomeViewModel
import com.example.phone_app.UI.Controllers.HomeController

class HomeViewModelFactory(private val homeController: HomeController): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(homeController) as T
    }

}