package com.example.phone_app.ui.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.phone_app.model.Products
import com.example.phone_app.R
import kotlinx.android.synthetic.main.products_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

class ProductAdapter(val phones: List<Products>, val clickListener: (Products) -> Unit, private val context: Context) : RecyclerView.Adapter<ProductAdapter.MovieViewHolder>() {

    var selectedStrings = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.products_layout, parent, false)
        )
    }

    override fun getItemCount() = phones.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var movie = phones[position]
      //  holder.view.id = movie.id
        holder.view.phoneName.text = movie.name
        holder.view.price.text = movie.price.toString() +" "+ "$"

       val imageView = holder.view.imageView6
      //  Picasso.get().load(movie.picture).resize(150,300).into(imageView)
        if(imageView.isSelected) {
        //    Picasso.get().load(movie.picture).resize(250,350).into(imageView)
        }else{
        //    Picasso.get().load(movie.picture).resize(150,300).into(imageView)
        }
      //  picture
        //  holder.view.textViewType.text = movie.type
      //  description
        //  holder.view.textViewRating.text = movie.rating

       // holder.view.textViewIsNew.visibility = if(movie.isNew == 1) View.VISIBLE else View.INVISIBLE
        val drinksNames = arrayOf("1", "2", "3", "4", "5", "Bottle")
        val drinksSpinnerAdapter =
            ArrayAdapter(this.context!!, R.layout.support_simple_spinner_dropdown_item, drinksNames)
        drinksSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1)
        holder.view.spinner2.adapter = drinksSpinnerAdapter

        holder.view.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                holder.view.spinner2.setSelection(0).toString()

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

              movie.quantity = parent.getItemAtPosition(position).toString()

            }
        }
          holder.view.add_cart.setOnClickListener {
              withMultiChoiceList(context,movie)

              clickListener(movie)


          }

    }
    fun withMultiChoiceList(context: Context,movie : Products) {

        val items = arrayOf("Cola", "Sprite","Orange Juice","Lemon Juice","Ice Tea","Sour cherry")
        val selectedList = ArrayList<Int>()
        val builder = AlertDialog.Builder(context)

        builder.setTitle("This is list choice dialog box")
        builder.setMultiChoiceItems(items, null
        ) { dialog, which, isChecked ->
            if (isChecked) {
                selectedList.add(which)
            } else if (selectedList.contains(which)) {
                selectedList.remove(Integer.valueOf(which))
            }
        }

        builder.setPositiveButton("DONE") { dialogInterface, i ->


            for (j in selectedList.indices) {
                selectedStrings.add(items[selectedList[j]])
            }

            Toast.makeText(context, "Items selected are: " + Arrays.toString(selectedStrings.toTypedArray()), Toast.LENGTH_SHORT).show()
           movie.name = movie.name+" "+Arrays.toString(selectedStrings.toTypedArray())

        }

        builder.show()

    }

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}