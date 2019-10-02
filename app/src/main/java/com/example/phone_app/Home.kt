package com.example.phone_app



import android.os.Bundle


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.Observer


import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phone_app.Data.Products
import com.example.phone_app.UI.Adapters.DrinksAdapter
import com.example.phone_app.UI.Adapters.ProductAdapter
import com.example.phone_app.UI.BaseFragment
import com.example.phone_app.UI.ViewModelFactory.HomeViewModelFactory
import com.example.phone_app.UI.ViewModels.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*

import org.kodein.di.KodeinAware

import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import kotlin.collections.ArrayList



class Home : BaseFragment(),KodeinAware {
    override fun layoutIname(): String {
        return "LoginFragment"
    }

    private var Position:Int=0

    var cachedList: MutableList<Products> = ArrayList()


    override val kodein by closestKodein()

    /* activity specific bindings */
    private val viewModelFactory: HomeViewModelFactory by instance()
    private var drinkSelected: String = "Vodka"


    var selectedStrings = ArrayList<String>()
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

       // val apiService = ProductApi(ConnectivityInterceptorImpl(this.context!!))
       // val productNetworkDataSource = ProductNetworkDataSourceImpl(apiService)
        viewModel.products.observe(this, Observer {

            val adapter = ProductAdapter(it, { position ->

                viewModel.addProduct(position)


            },requireContext(),drinkSelected)

            recyclerproducts.adapter = adapter
            recyclerproducts.layoutManager = LinearLayoutManager(this.context)


            //  textView4.text=it.toString()
            //cachedList.addAll(listOf(it))
        })

        val drinksNames = arrayOf("Vodka", "Rum", "Gin","Others", "Tequila", "Vodka", "Rum", "Gin", "Tequila", "Vodka", "Rum", "Gin", "Tequila")
        //drinksSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1)
        spinner.adapter = DrinksAdapter(this.requireContext(), drinksNames)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
           override fun onNothingSelected(p0: AdapterView<*>?) {
                spinner.setSelection(Position).toString()

                drinkSelected="Vodka"
                viewModel.getUsers(drinkSelected)
            }
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                drinkSelected =  parent.getItemAtPosition(position).toString()
                Position=position
                viewModel.getUsers(drinkSelected)
            }
        }
    }

}