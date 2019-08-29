package com.example.phone_app.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phone_app.ui.viewmodels.ProfileViewModel
import com.example.phone_app.ui.controllers.ProfileController

class ProfileViewModelFactory(private val profileController: ProfileController): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(profileController) as T
    }
}