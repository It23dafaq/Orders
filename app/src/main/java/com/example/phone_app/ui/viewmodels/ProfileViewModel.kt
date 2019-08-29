package com.example.phone_app.ui.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.phone_app.model.Person
import com.example.phone_app.ScopedViewModel
import com.example.phone_app.ui.controllers.ProfileController

class ProfileViewModel(private val profileController: ProfileController) : ScopedViewModel(){
    // TODO: Implement the ViewModel
    private val users: MutableLiveData<Person>  by lazy {
        MutableLiveData<Person>().also {
            loadUsers()

        }
    }

    fun getUsers(): LiveData<Person> {
        return users
    }

    private fun loadUsers() {
       }
}
