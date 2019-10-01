package com.example.phone_app.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.phone_app.Data.Orders
import com.example.phone_app.Data.OrdersByname
import com.example.phone_app.R
import kotlinx.android.synthetic.main.recyclerbyname.view.*

class OrderAdapterByname(val phones: List<OrdersByname>,var total:TextView): RecyclerView.Adapter<OrderAdapterByname.OrderViewHolder>() {

private var totalPrice:Double = 0.0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerbyname, parent, false)
        )
    }

    override fun getItemCount() = phones.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val movie = phones[position]
        //  holder.view.id = movie.id
        holder.view.NameOfWaiter.text = movie.Username
        holder.view.drinkBywaiter.text = movie.Drink
        holder.view.QuantityBywaiter.text = movie.posotita
        holder.view.Time_Orderbywaiter.text = movie.Time
        holder.view.PriceBywaiter.text=movie.price.toString()
        totalPrice +=movie.TotalPrice
        total.text=totalPrice.toString()



        //  holder.view.textViewType.text = movie.type
        //  description
        //  holder.view.textViewRating.text = movie.rating

        // holder.view.textViewIsNew.visibility = if(movie.isNew == 1) View.VISIBLE else View.INVISIBLE


    }


    class OrderViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
