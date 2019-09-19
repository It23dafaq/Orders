package com.example.phone_app.UI.Admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.phone_app.R
import com.example.phone_app.UI.Adapters.OrderAdapter
import com.example.phone_app.UI.BaseFragment
import com.example.phone_app.UI.ViewModelFactory.HomeViewModelFactory
import com.example.phone_app.UI.ViewModels.HomeViewModel
import kotlinx.android.synthetic.main.activity_daily_orders.*
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class Admin_Orders : BaseFragment() {
    override fun layoutIname(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val kodein by closestKodein()
    private val viewModelFactory: HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_daily_orders, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        viewModel.getOrders("Generic")

        viewModel.ordersDaily.observe(this, Observer {

            val adapter = OrderAdapter(it)
            daily_recycler.adapter = adapter
            daily_recycler.layoutManager = LinearLayoutManager(this.context)
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Admin_Orders().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
