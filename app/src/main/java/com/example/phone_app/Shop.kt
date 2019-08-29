package com.example.phone_app


import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phone_app.ui.adapters.cartAdapter
import com.example.phone_app.ui.viewmodelfactory.HomeViewModelFactory
import com.example.phone_app.ui.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.shop_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber


class Shop : Fragment() , KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: HomeViewModelFactory by instance()
    companion object {

        @JvmStatic
        fun newInstance() =
            Shop().apply {
                arguments = Bundle().apply {
                    // putString(ARG_PARAM1, param1)
                }
            }
    }



    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shop_fragment, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
       // val bundle = arguments

        //   val cartArray: List<Products> = bundle!!.getParcelableArrayList<Products>("da")
        val cart = viewModel.getProduct()
        val adapter = cartAdapter(cart){
            viewModel.RemoveProduct(it)

        }

        shopcart.adapter=adapter
        shopcart.layoutManager = LinearLayoutManager(context!!)
     if(viewModel.GetSize()==0){
         confirmOrder.visibility = View.GONE
     }else{
         confirmOrder.visibility = View.VISIBLE
     }


     confirmOrder.setOnClickListener {
        val totalPayment = viewModel.GetPrice()
         Log.d("total",totalPayment.toString())
         val builder = AlertDialog.Builder(this.context!!)

         // Set the alert dialog title
         builder.setTitle("Payment")

         // Display a message on alert dialog
         builder.setMessage("The total price is "+" "+totalPayment.toString()+" "+"do you want to continue ?")

         // Set a positive button and its click listener on alert dialog
         builder.setPositiveButton("YES"){dialog, which ->
             // Do something when user press the positive button

         }


         // Display a negative button on alert dialog
         builder.setNegativeButton("No"){dialog,which ->
            Timber.i("Payment canceled")
         }




         // Finally, make the alert dialog using builder
         val dialog: AlertDialog = builder.create()

         // Display the alert dialog on app interface
         dialog.show()
     }
    }
     }





