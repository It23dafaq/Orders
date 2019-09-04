package com.example.phone_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_spinner_layout.view.*


class DrinksAdapter(var context: Context,  var drinksNames: Array<String>) : BaseAdapter(),
    SpinnerAdapter {

    override fun getCount(): Int {
        return drinksNames.size
    }

    override fun getItem(position: Int): Any {
        return drinksNames[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view =  View.inflate(context,R.layout.item_spinner_layout, null)
        view?.textView6?.text = drinksNames[position]
        Picasso.get().load(R.drawable.green_table).into(view?.imageView)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        return super.getDropDownView(position, convertView, parent)
    }
}