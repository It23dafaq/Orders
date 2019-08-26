package com.example.phone_app.UI.ViewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.phone_app.Data.Person
import com.example.phone_app.ScopedViewModel
import com.example.phone_app.UI.Controllers.ProfileController

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
