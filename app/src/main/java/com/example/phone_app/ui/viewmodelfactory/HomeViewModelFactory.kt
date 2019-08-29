package com.example.phone_app.ui.viewmodelfactory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phone_app.ui.viewmodels.HomeViewModel
import com.example.phone_app.ui.controllers.HomeController

class HomeViewModelFactory(private val homeController: HomeController): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(homeController) as T
    }

}