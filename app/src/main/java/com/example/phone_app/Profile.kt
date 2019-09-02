package com.example.phone_app


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phone_app.Network.ConnectivityInterceptorImpl
import com.example.phone_app.Network.TablesApi
import com.example.phone_app.UI.Adapters.TableAdapter
import com.example.phone_app.UI.ViewModelFactory.ProfileViewModelFactory
import com.example.phone_app.UI.ViewModels.ProfileViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance



class Profile : Fragment(),KodeinAware {
    override val kodein by closestKodein()
    var Id:String = ""

    /* activity specific bindings */
    private val viewModelFactory: ProfileViewModelFactory by instance()
    companion object {

        @JvmStatic
        fun newInstance() =
            Profile().apply {
                arguments = Bundle().apply {
                   putString("ID", "gjgjgjg")

                }
            }
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.getUsers()
        val apiServic = TablesApi(ConnectivityInterceptorImpl(this.context!!))
       // val productNetworkDataSource = ProductNetworkDataSourceImpl(apiServic)
        viewModel.products.observe(this, Observer {

            val adapter = TableAdapter(it){
               Id = it.ID.toString()
            }

            tableRecyclerView.adapter = adapter
            tableRecyclerView.layoutManager = LinearLayoutManager(this.context)
            })




        }

    }



