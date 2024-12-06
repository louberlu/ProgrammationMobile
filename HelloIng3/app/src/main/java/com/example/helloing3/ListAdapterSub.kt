package com.example.helloing3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ListAdapterSub(context: Context, dataArrayList: ArrayList<ListDataSub?>?):
    ArrayAdapter<ListDataSub?>(context, R.layout.list_item, dataArrayList!!) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val listData = getItem(position)

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val subject = view!!.findViewById<TextView>(R.id.subject_TextView)
        val heure = view.findViewById<TextView>(R.id.heure_textView)
        val coeff = view.findViewById<TextView>(R.id.coef_textView)

        subject.text = listData!!.subject
        heure.text = listData.heure
        coeff.text = listData.coeff

        return view
    }

}