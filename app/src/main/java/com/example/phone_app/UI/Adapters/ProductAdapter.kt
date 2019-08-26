package com.example.phone_app.UI.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phone_app.Data.Products
import com.example.phone_app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.products_layout.view.*

class ProductAdapter(val phones: List<Products>, val clickListener: (Products) -> Unit) : RecyclerView.Adapter<ProductAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.products_layout, parent, false)
        )
    }

    override fun getItemCount() = phones.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = phones[position]
      //  holder.view.id = movie.id
        holder.view.phoneName.text = movie.name
        holder.view.price.text = movie.price.toString() +" "+ "$"
        holder.view.description.text = movie.description
       val imageView = holder.view.imageView6
        Picasso.get().load(movie.picture).resize(150,300).into(imageView)
        if(imageView.isSelected) {
            Picasso.get().load(movie.picture).resize(250,350).into(imageView)
        }else{
            Picasso.get().load(movie.picture).resize(150,300).into(imageView)
        }
      //  picture
        //  holder.view.textViewType.text = movie.type
      //  description
        //  holder.view.textViewRating.text = movie.rating

       // holder.view.textViewIsNew.visibility = if(movie.isNew == 1) View.VISIBLE else View.INVISIBLE
          holder.view.add_cart.setOnClickListener {
              clickListener(movie)


          }

    }


    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}