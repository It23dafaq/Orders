package com.example.phone_app.UI.ViewModels



import com.example.phone_app.Data.Products
import com.example.phone_app.ScopedViewModel
import com.example.phone_app.UI.Controllers.HomeController
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
    fun getUsers(){
        launchWithLoad({
            homeController.getUsers()
        }){}
    }



    }










