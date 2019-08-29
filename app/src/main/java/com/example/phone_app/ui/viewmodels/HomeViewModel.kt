package com.example.phone_app.ui.viewmodels



import com.example.phone_app.model.Products
import com.example.phone_app.ScopedViewModel
import com.example.phone_app.ui.controllers.HomeController
import java.math.BigDecimal

var addcart : MutableList<Products> = ArrayList()
class HomeViewModel(private val homeController: HomeController) : ScopedViewModel() {
    // TODO: Implement the ViewModel

    var products = homeController.downloadProduct
    fun addProduct(list: Products) {
        addcart.add(list)

    }
    fun RemoveProduct(id: Int) {
        addcart.removeAt(id)
    }
    fun GetPrice():BigDecimal{
        return  addcart.sumByDouble { it.price}.toBigDecimal()
    }
    fun  getProduct(): MutableList<Products> {
        return addcart
    }
    fun GetSize():Int{
        return  addcart.size
    }
    fun getUsers(string: String){
        launchWithLoad({
            homeController.getUsers(string)
        }){}
    }



    }










