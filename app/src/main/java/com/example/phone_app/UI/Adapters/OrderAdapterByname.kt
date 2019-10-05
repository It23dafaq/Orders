package com.example.phone_app.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.phone_app.Data.OrdersByname
import com.example.phone_app.R
import kotlinx.android.synthetic.main.recyclerbyname.view.*

class OrderAdapterByname(val phones: List<OrdersByname>,var total:TextView): RecyclerView.Adapter<OrderAdapterByname.OrderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
       val viewHolder=OrderViewHolder(
           LayoutInflater.from(parent.context)
               .inflate(R.layout.recyclerbyname, parent, false)
       )

        return viewHolder
    }

    override fun getItemCount() = phones.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val movie = phones[position]
        //  holder.view.id = movie.id
        holder.view.drinknamecart.text = movie.Username
        holder.view.quantitycart.text = movie.Drink
        holder.view.dayliquantityCart.text = movie.posotita
        holder.view.Time_Orderbywaiter.text = movie.Time
        holder.view.dailyTotalPrice.text=movie.price.toString()
       



        //  holder.view.textViewType.text = movie.type
        //  description
        //  holder.view.textViewRating.text = movie.rating

        // holder.view.textViewIsNew.visibility = if(movie.isNew == 1) View.VISIBLE else View.INVISIBLE


    }


    class OrderViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
