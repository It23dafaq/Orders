package com.example.phone_app.UI.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phone_app.UI.ViewModels.ProfileViewModel
import com.example.phone_app.UI.Controllers.ProfileController

class ProfileViewModelFactory(private val profileController: ProfileController): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(profileController) as T
    }
}