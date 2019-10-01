package com.example.phone_app


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast


import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phone_app.Network.ConnectivityInterceptorImpl
import com.example.phone_app.Network.TablesApi
import com.example.phone_app.UI.Adapters.TableAdapter
import com.example.phone_app.UI.ViewModelFactory.ProfileViewModelFactory
import com.example.phone_app.UI.ViewModels.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_admin_filter_by_name.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance



class Profile : Fragment(),KodeinAware {
    override val kodein by closestKodein()


    /* activity specific bindings */
    private val viewModelFactory: ProfileViewModelFactory by instance()
    companion object {

         var Id: String = ""

        @JvmStatic
        fun newInstance():Profile{
            val frag = Profile()
            val bundle = Bundle()
            bundle.putString("ID",Id)
            frag.arguments = bundle
            return frag
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
        viewModel.products.observe(this, Observer { it ->

            val adapter = TableAdapter(it, {

                Id =it.ID.toString()

            },this.context!!)

            tableIDtext.setOnEditorActionListener { v, actionId, event ->
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId== EditorInfo.IME_ACTION_GO ||actionId== EditorInfo.IME_ACTION_NEXT || actionId== EditorInfo.IME_ACTION_SEARCH ){

                    var numeric = true
                    numeric =  tableIDtext.text.toString().matches("-?\\d+(\\.\\d+)?".toRegex())
                    if(numeric && tableIDtext.text.toString().toInt()<= 34 && tableIDtext.text.toString().toInt()>=1) {
                        tableRecyclerView.scrollToPosition(tableIDtext.text.toString().toInt()-1)
                    }else{
                        Toast.makeText(requireContext(),"You have to place number smaller than 34 and bigger than 0",Toast.LENGTH_SHORT).show()
                        tableIDtext.text.clear()
                    }

                    false
                } else {
                    true
                }
            }



            tableRecyclerView.adapter = adapter
            tableRecyclerView.layoutManager = LinearLayoutManager(this.context)
            })




        }

    }



