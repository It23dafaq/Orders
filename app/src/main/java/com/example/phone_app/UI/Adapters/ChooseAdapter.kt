package com.example.phone_app.UI.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import android.widget.BaseAdapter
import com.example.phone_app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_spinner_layout.view.*
import kotlinx.android.synthetic.main.spiner_admin_choose.view.*


class ChooseAdapter(var context: Context,  var filterName: Array<String>) : BaseAdapter(),
    SpinnerAdapter {

    override fun getCount(): Int {
        return filterName.size
    }

    override fun getItem(position: Int): Any {
        return filterName[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view =  View.inflate(context, R.layout.spiner_admin_choose, null)
        view?.choose?.text = filterName[position]
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        return super.getDropDownView(position, convertView, parent)
    }
}