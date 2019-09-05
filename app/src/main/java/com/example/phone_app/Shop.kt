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
import com.example.phone_app.Data.Products
import com.example.phone_app.UI.Adapters.cartAdapter
import com.example.phone_app.UI.ViewModelFactory.HomeViewModelFactory
import com.example.phone_app.UI.ViewModels.HomeViewModel
import kotlinx.android.synthetic.main.shop_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import timber.log.Timber
import android.R.string.cancel
import android.content.DialogInterface
import android.text.InputType
import android.widget.EditText




class Shop : Fragment() , KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: HomeViewModelFactory by instance()
    var Id:String = ""
    private var comments = ""
    companion object {

        @JvmStatic
        fun newInstance() =
            Shop().apply {
                arguments = Bundle().apply {
                   //  getString("ID",Id)
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

       Id = Profile.Id
        //   val cartArray: List<Products> = bundle!!.getParcelableArrayList<Products>("da")
        val cart = viewModel.getProduct()
        val adapter = cartAdapter(cart){
             if(!cart.isNullOrEmpty()) {
                 if(it!=-1) {
                     viewModel.RemoveProduct(it)
                 }
                 }

        }

        if(Id.equals("")){
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
         builder.setPositiveButton("YES") { dialog, which ->
             // Do something when user press the positive button
             var quan = 0
             var drinkname = ""
             for (item in cart) {
                 quan = quan.plus(item.quantity.toInt())
                 drinkname = drinkname + item.name
             }
             InsertOrder(cart, quan.toString(), drinkname, totalPayment.toDouble(),adapter)

             // viewModel.insertORDERS(quan.toString(),com.example.phone_app.Data.Person.email,drinkname,totalPayment.toDouble(),"")
         }

             // Display a negative button on alert dialog
             builder.setNegativeButton("No") { dialog, which ->
                 Timber.i("Payment canceled")
             }


         // Finally, make the alert dialog using builder
         val dialog: AlertDialog = builder.create()

         // Display the alert dialog on app interface
         dialog.show()

    }
        comment_btn.setOnClickListener {
            ShowCommentDialog()

        }

     }
    fun InsertOrder(cart:MutableList<Products>,quan : String,drinkname:String,totalPayment:Double,adapter: cartAdapter){
        val SignUpUrl = "https://rectifiable-merchan.000webhostapp.com/InsertOrder.php?Posotita="+quan+
                "&UserName="+com.example.phone_app.Data.Person.email+"&DrinkName="+drinkname+"&Price="+totalPayment+"&Comments="+comments

        val requestQ = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(Request.Method.GET,SignUpUrl, Response.Listener{ response ->
            if(response.equals("succes"))
            {
                cart.clear()
                adapter.notifyDataSetChanged()

            }else {
                Log.d("some","something went wrong")
            }




        }, Response.ErrorListener { error ->
            Log.d("lol",error.message.toString())
        })
        requestQ.add(stringRequest)
    }
    fun ShowCommentDialog(){
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("Add Comments")

// Set up the input
        val input = EditText(context)
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        builder.setView(input)

// Set up the buttons
        builder.setPositiveButton(
            "OK"
        ) { dialog, which -> comments = input.text.toString() }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, which -> dialog.cancel() }

        builder.show()
    }

     }





