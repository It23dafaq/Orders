package com.example.phone_app.UI.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.phone_app.Data.Products
import com.example.phone_app.Data.tables
import com.example.phone_app.Profile
import com.example.phone_app.R
import kotlinx.android.synthetic.main.table_layout.view.*
import timber.log.Timber

private var boolean : Boolean=true
class TableAdapter(val phones: List<tables>,val clickListener: (tables) -> Unit,val context:Context): RecyclerView.Adapter<TableAdapter.CartViewHolder>() {

    companion object{
        var TRUE_DATABASE_RESPONSE = 1
        var NOT_TRUE_DB_RESPONSE = 0
    }
    private var lastSelected : View? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val viewHolder = CartViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.table_layout, parent, false)
        )

        if (viewHolder.adapterPosition >= 0) {
            val movie = phones[viewHolder.adapterPosition]
            if (movie.isOkay == 0) {
                if (movie.isOkay == 1) {
                    if (!Profile.Id.isNullOrEmpty()) {
                        if (movie.ID == phones[Profile.Id.toInt() - 1].ID) {
                            viewHolder.view.tablegreenimg.setImageResource(R.drawable.red_table1)
                            // phones[Profile.Id.toInt()-1].isOkay=1
                        } else {
                            viewHolder.view.tablegreenimg.setImageResource(R.drawable.green_table1)
                        }
                    }
                } else if (movie.isOkay == 0) {
                    viewHolder.view.tablegreenimg.setImageResource(R.drawable.red_table1)
                } else {

                    viewHolder.view.tablegreenimg.setImageResource(R.drawable.green_table1)

                }
            }
        }
        viewHolder.view.setOnClickListener {
            if (viewHolder.adapterPosition >= 0) {
                val movie = phones[viewHolder.adapterPosition]
                if (movie.isOkay == 0) {
                    if ((movie.quantity != "0")) {
                        val builder = AlertDialog.Builder(context)

                        // Set the alert dialog title
                        builder.setTitle("Payment")

                        // Display a message on alert dialog
                        builder.setMessage("did you paid this table")

                        // Set a positive button and its click listener on alert dialog
                        builder.setPositiveButton("YES") { dialog, which ->
                            // Do something when user press the positive button
                            UpdateTable(movie)
                            viewHolder.view.tablegreenimg.setImageResource(R.drawable.green_table1)
                            movie.isOkay = 1
                            clickListener(movie)
                        }
                        // viewModel.insertORDERS(quan.toString(),com.example.phone_app.Data.Person.email,drinkname,totalPayment.toDouble(),"")

                        // Display a negative button on alert dialog
                        builder.setNegativeButton("No") { dialog, which ->
                            Timber.i("Payment canceled")
                        }
                        // Finally, make the alert dialog using builder and show it
                         builder.create().show()
                    } else {
                        viewHolder.view.tablegreenimg.setImageResource(R.drawable.green_table1)
                        movie.isOkay = TRUE_DATABASE_RESPONSE
                        clickListener(movie)
                    }
                } else if (movie.isOkay == TRUE_DATABASE_RESPONSE) {
                    lastSelected?.tablegreenimg?.setImageResource(R.drawable.green_table1)
                    viewHolder.view.tablegreenimg.setImageResource(R.drawable.red_table1)
                    lastSelected = viewHolder.view
                    movie.isOkay = NOT_TRUE_DB_RESPONSE
                    clickListener(movie)
                }
            }
        }
        return viewHolder
    }

    override fun getItemCount() = phones.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val movie = phones[position]

        //  holder.view.id = movie.id
        holder.view.tableID.text = movie.ID.toString()
        holder.view.tableQuantity.text =movie.quantity
        holder.view.TableCost.text = movie.price.toString()

        //  holder.view.textViewType.text = movie.type
        //  description
        //  holder.view.textViewRating.text = movie.rating

        // holder.view.textViewIsNew.visibility = if(movie.isNew == 1) View.VISIBLE else View.INVISIBLE


    }
    fun UpdateTable(movie:tables){
        val SignUpUrl = "https://rectifiable-merchan.000webhostapp.com/UpdateTable.php?Name="+
                "&Quantity=0&TotalPrice="+"&isOkay="+1+"&ID="+movie.ID

        val requestQ = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(Request.Method.GET,SignUpUrl, Response.Listener{ response ->
            if(response.equals("updated"))
            {
                Profile.Id=""

            }else {
                Log.d("some","something went wrong")
                Profile.Id=""
            }




        }, Response.ErrorListener { error ->
            Log.d("lol",error.message.toString())
        })
        requestQ.add(stringRequest)
    }


    class CartViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}