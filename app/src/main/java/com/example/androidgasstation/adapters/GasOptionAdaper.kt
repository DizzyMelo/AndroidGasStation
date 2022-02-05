package com.example.androidgasstation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.androidgasstation.R
import com.example.androidgasstation.entities.Gas

class GasOptionAdaper(private val context: Context,
                      private val dataSource: Array<Gas>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView =  inflater.inflate(R.layout.list_item, parent, false)
        var title = rowView.findViewById<TextView>(R.id.gas_name)
        var price = rowView.findViewById<TextView>(R.id.gas_price)

        title.text = dataSource[position].name
        price.text = dataSource[position].price.toString()
        return rowView
    }


}
