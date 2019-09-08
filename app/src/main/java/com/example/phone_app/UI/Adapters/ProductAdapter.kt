package com.example.phone_app.UI.Adapters


import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.phone_app.Data.Products
import com.example.phone_app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.products_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

class ProductAdapter(val phones: List<Products>, val clickListener: (Products) -> Unit, private val context: Context,private var item_spiner:String) : RecyclerView.Adapter<ProductAdapter.MovieViewHolder>() {
    lateinit var items :Array<String>
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
      /*
        val drinksNames = arrayOf("1", "2", "3", "4", "5", "Bottle")
        val drinksSpinnerAdapter =
            ArrayAdapter(this.context!!, R.layout.support_simple_spinner_dropdown_item, drinksNames)
        drinksSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1)
        holder.view.spinner2.adapter = drinksSpinnerAdapter

        holder.view.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                holder.view.spinner2.setSelection(position).toString()

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

              movie.quantity = parent.getItemAtPosition(position).toString()

            }
        }
        */
          holder.view.add_cart.setOnClickListener {
              withMultiChoiceList(context,movie,item_spiner)

              clickListener(movie)


          }

    }
    fun withMultiChoiceList(context: Context,movie : Products,item_spiner: String) {
          if(item_spiner.equals("Rum")) {
               items = arrayOf("Cola", "Sprite", "Orange Juice", "Lemon Juice", "Ice Tea", "Sour cherry")
          }else if(item_spiner.equals("Vodka")){
              items = arrayOf("Lemonade", "Sprite", "Orange Juice", "Lemon Juice","Cola","Ice Tea", "Sour cherry")
          }else if(item_spiner.equals("Tequila")){
              items = arrayOf("Redbull", "Sprite", "Orange Juice", "Lemon Juice","Cola", "Ice Tea", "Sour cherry")
          }else{
              items = arrayOf("Cola", "Sprite", "Orange Juice", "Lemon Juice", "Ice Tea", "Sour cherry")
          }
        val selectedList = ArrayList<Int>()
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Others")
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
            withChoiceList(movie)

        }

        builder.show()

    }
    fun withChoiceList(movie : Products) {


        val drinkquantity = arrayOf("1", "2", "3", "4", "5", "Bottle")
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Quantity")
        builder.setCancelable(false)
        builder.setItems(drinkquantity, { _, which ->
            // Get the dialog selected item
            val selected = drinkquantity[which]
            if (selected.equals("Bottle")) {
                movie.quantity = 10
                movie.bottle=10
            } else{
                movie.quantity = selected.toInt()
                movie.bottle=0
        }

        })



        builder.show()

    }

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}