package com.example.helloing3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ListAdapter(context: Context, dataArrayList: ArrayList<ListData?>?):
ArrayAdapter<ListData?>(context, R.layout.item_list, dataArrayList!!){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val listData = getItem(position)

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        }

        val listImage = view!!.findViewById<ImageView>(R.id.list_ImageView)
        val listName = view.findViewById<TextView>(R.id.listName_textView)
        val listFonction = view.findViewById<TextView>(R.id.listFonction_textView)

        listImage.setImageResource(listData!!.image)
        listName.text = listData.name
        listFonction.text = listData.fonction

        return view
    }
}