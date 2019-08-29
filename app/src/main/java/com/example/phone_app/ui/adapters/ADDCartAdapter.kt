package com.example.phone_app.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phone_app.model.Products
import com.example.phone_app.R
import kotlinx.android.synthetic.main.fragment_cart_view.view.*

class cartAdapter(val phones: List<Products>,  val clickListener: (Int) -> Unit): RecyclerView.Adapter<cartAdapter.CartViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_cart_view, parent, false)
        )
    }

    override fun getItemCount() = phones.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val movie = phones[position]
        //  holder.view.id = movie.id
        holder.view.phone_cart.text = movie.name
        holder.view.price_cart.text = movie.price.toString()
        holder.view.quantity.text = movie.quantity
       holder.view.imageView4.setOnClickListener {
           clickListener (holder.adapterPosition)
           notifyItemRemoved(holder.adapterPosition)
       }
        //  holder.view.textViewType.text = movie.type
        //  description
        //  holder.view.textViewRating.text = movie.rating

        // holder.view.textViewIsNew.visibility = if(movie.isNew == 1) View.VISIBLE else View.INVISIBLE



    }


    class CartViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}