package com.example.phone_app



import android.app.Application
import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer


import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phone_app.Data.Products
import com.example.phone_app.Network.ConnectivityInterceptorImpl
import com.example.phone_app.Network.ProductApi
import com.example.phone_app.Network.ProductNetworkDataSourceImpl
import com.example.phone_app.UI.Adapters.ProductAdapter
import com.example.phone_app.UI.BaseFragment
import com.example.phone_app.UI.ViewModelFactory.HomeViewModelFactory
import com.example.phone_app.UI.ViewModels.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*

import org.kodein.di.KodeinAware

import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.util.*
import kotlin.collections.ArrayList


private var Position:Int=0

class Home : BaseFragment(),KodeinAware {
    override fun layoutIname(): String {
        return "LoginFragment"
    }


    var cachedList: MutableList<Products> = ArrayList()


    override val kodein by closestKodein()

    /* activity specific bindings */
    private val viewModelFactory: HomeViewModelFactory by instance()
    private var drinkSelected: String = ""

    private var id = ""

    companion object {

        var shop: MutableList<Products> = ArrayList()

        @JvmStatic
        fun newInstance() =
            Home().apply {
                arguments = Bundle().apply {


                }

            }
    }

    private lateinit var viewModel: HomeViewModel
    //    private val cachedList: LiveData<List<Products>> = List<Products>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
       // viewModel.getUsers(drinkSelected)

        val apiServic = ProductApi(ConnectivityInterceptorImpl(this.context!!))
        val productNetworkDataSource = ProductNetworkDataSourceImpl(apiServic)
        viewModel.products.observe(this, Observer {

            val adapter = ProductAdapter(it, { position ->
                viewModel.addProduct(position)


            },this.context!!)

            recyclerproducts.adapter = adapter
            recyclerproducts.layoutManager = LinearLayoutManager(this.context)


            //  textView4.text=it.toString()
            //cachedList.addAll(listOf(it))
        })

        val drinksNames = arrayOf("Vodka", "Rum", "Gin", "Tequila")
        val drinksSpinnerAdapter =
            ArrayAdapter(this.context!!, R.layout.support_simple_spinner_dropdown_item, drinksNames)
        drinksSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1)
        spinner.adapter = drinksSpinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                spinner.setSelection(Position).toString()
                viewModel.getUsers(drinkSelected)
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

             drinkSelected =  parent.getItemAtPosition(position).toString()
                Position=position
                viewModel.getUsers(drinkSelected)
            }
            /*
          productNetworkDataSource.downloadProduct.observe(this, Observer {

              //textView4.text= it.toString()


              val adapter = ProductAdapter(cachedList) { position ->
                  shop.addAll(listOf(position))


              }
              recyclerproducts.adapter = adapter
              recyclerproducts.layoutManager = LinearLayoutManager(this.context)


              //  textView4.text=it.toString()
              cachedList.addAll(it!!)

          })
  */


            //   GlobalScope.launch(Dispatchers.Main) {
            //       productNetworkDataSource.fetchCurrentWeather()
            //   }


        }

    }


}



