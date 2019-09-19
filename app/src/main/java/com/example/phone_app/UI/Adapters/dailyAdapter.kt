package com.example.phone_app.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phone_app.Data.Orders
import com.example.phone_app.R
import kotlinx.android.synthetic.main.fragment_cart_view.view.*
import kotlinx.android.synthetic.main.order_recycle_view.view.drinksdaily

class dailyAdapter(val phones: List<Orders>): RecyclerView.Adapter<dailyAdapter.CartViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.dailyrecyclerview, parent, false)
        )
    }

    override fun getItemCount() = phones.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val movie = phones[position]
        //  holder.view.id = movie.id
        holder.view.drinksdaily.text = movie.Drink
        holder.view.pricedaily.text = movie.price.toString()
        holder.view.quantitydaily.text = movie.posotita


    }


    class CartViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}