package com.example.phone_app.UI.ViewModels



import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.phone_app.Data.Products
import com.example.phone_app.Network.OrderNetworkDatasource
import com.example.phone_app.ScopedViewModel
import com.example.phone_app.UI.Controllers.HomeController
import com.example.phone_app.UI.Controllers.OrderController
import java.math.BigDecimal

var addcart : MutableList<Products> = ArrayList()
class HomeViewModel(private val homeController: HomeController,private val OrderController:OrderController) : ScopedViewModel() {
    // TODO: Implement the ViewModel

    var products = homeController.downloadProduct
    var ordersDaily = OrderController.downloadProduct
    fun addProduct(list: Products) {
        addcart.add(list)
    }

    fun RemoveProduct(id: Int) {
        addcart.removeAt(id)
    }

    fun GetPrice(): BigDecimal {

        return addcart.sumByDouble { it.price * it.quantity +it.bottle }.toBigDecimal()
    }

    fun getProduct(): MutableList<Products> {
        return addcart
    }

    fun GetSize(): Int {
        return addcart.size
    }

    fun getUsers(string: String) {
        launchWithLoad({
            homeController.getUsers(string)
        }) {}


    }
    fun getOrders(filter:String) {
        launchWithLoad({
            OrderController.fetchOrders(filter)
        }) {}


    }
    fun getOrdersByname(name:String,filter:String){
        launchWithLoad({
            OrderController.fetchOrdersByname(filter,name)
        }) {}
    }

    fun insertORDERS(quantity: String, username: String, DrinkName: String, Price: Double, Comments: String) {

        launchWithLoad({
            homeController.inserOrders(quantity,username,DrinkName,Price,Comments)
        }) {}
    }
}










