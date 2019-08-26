package com.example.phone_app.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phone_app.Data.Products
import com.example.phone_app.R
import kotlinx.android.synthetic.main.fragment_cart_view.view.*
import kotlinx.android.synthetic.main.table_layout.view.*
private var boolean : Boolean=true
class TableAdapter(val phones: List<Products>, val clickListener: (Int) -> Unit): RecyclerView.Adapter<TableAdapter.CartViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.table_layout, parent, false)
        )
    }

    override fun getItemCount() = 35

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val movie = phones[position]
        //  holder.view.id = movie.id
        holder.view.tableID.text = position.toString()
        holder.view.tableQuantity.text =movie.quantity
        holder.view.TableCost.text = movie.price.toString()
        holder.view.tablegreenimg.setOnClickListener {
            if(boolean==true) {
                holder.view.tablegreenimg.setImageResource(R.drawable.red_table)
                boolean=false
            }else{
                holder.view.tablegreenimg.setImageResource(R.drawable.green_table)
                boolean=true
            }

        }
        //  holder.view.textViewType.text = movie.type
        //  description
        //  holder.view.textViewRating.text = movie.rating

        // holder.view.textViewIsNew.visibility = if(movie.isNew == 1) View.VISIBLE else View.INVISIBLE


    }


    class CartViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}