package com.example.phone_app


import android.app.Person
import android.content.Context
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.phone_app.UI.Adapters.cartAdapter
import com.example.phone_app.UI.ViewModelFactory.HomeViewModelFactory
import com.example.phone_app.UI.ViewModels.HomeViewModel
import kotlinx.android.synthetic.main.shop_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber


class Shop : Fragment() , KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: HomeViewModelFactory by instance()
    var Id:String = ""
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
        arguments?.getString("ID")?.let {
            Id = it
        }
        //   val cartArray: List<Products> = bundle!!.getParcelableArrayList<Products>("da")
        val cart = viewModel.getProduct()
        val adapter = cartAdapter(cart){
             if(!cart.isNullOrEmpty()) {
                 if(it!=-1) {
                     viewModel.RemoveProduct(it)
                 }
                 }

        }
        if(Id.equals(" ")){
            tableID.text = "choose id"
        }else{
            tableID.text=Id
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
             var quan=0
             var drinkname=""
             for(item in cart){
              quan=quan.plus(item.quantity.toInt())
                drinkname=drinkname+item.name
                }
             val SignUpUrl = "https://rectifiable-merchan.000webhostapp.com/InsertOrder.php?Posotita="+quan+
                     "&UserName="+com.example.phone_app.Data.Person.email+"&DrinkName="+drinkname+"&Price="+totalPayment+"&Comments="+""

             val requestQ = Volley.newRequestQueue(context)
             val stringRequest = StringRequest(Request.Method.GET,SignUpUrl, Response.Listener{ response ->
                 if(response.equals("succes"))
                 {
                     cart.clear()
                 }else {
                     Log.d("some","something went wrong")
                 }




             }, Response.ErrorListener { error ->
                 Log.d("lol",error.message.toString())
             })
             requestQ.add(stringRequest)
         }

            // viewModel.insertORDERS(quan.toString(),com.example.phone_app.Data.Person.email,drinkname,totalPayment.toDouble(),"")



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





