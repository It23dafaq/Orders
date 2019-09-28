package com.example.phone_app.UI.Admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.phone_app.R
import com.example.phone_app.UI.Adapters.ChooseAdapter
import com.example.phone_app.UI.Adapters.DrinksAdapter
import com.example.phone_app.UI.Adapters.OrderAdapter
import com.example.phone_app.UI.Adapters.dailyAdapter
import com.example.phone_app.UI.ViewModelFactory.HomeViewModelFactory
import com.example.phone_app.UI.ViewModels.HomeViewModel
import kotlinx.android.synthetic.main.activity_daily_orders.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.order_daily.*
import kotlinx.android.synthetic.main.spiner_admin_choose.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class Filter_Admin_fragment : Fragment() ,KodeinAware{
    override val kodein by closestKodein()
    private val viewModelFactory: HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel
    private var Position:Int=0
    private var drinkSelected:String="Today"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_daily, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        val drinksNames = arrayOf("Today","Week","Month","Year")
        //drinksSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1)
        spinner2.adapter = ChooseAdapter(this.requireContext(), drinksNames)

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                spinner2.setSelection(Position).toString()


                viewModel.getOrders("Today")
            }
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                drinkSelected =  parent.getItemAtPosition(position).toString()
                Position=position
                viewModel.getOrders(drinkSelected)

            }
        }


        viewModel.ordersDaily.observe(this, Observer {

            val adapter = dailyAdapter(it)
            da_recy.adapter = adapter
            da_recy.layoutManager = LinearLayoutManager(this.context)
        })


    }


    companion object {

        @JvmStatic
        fun newInstance() =
            Filter_Admin_fragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
