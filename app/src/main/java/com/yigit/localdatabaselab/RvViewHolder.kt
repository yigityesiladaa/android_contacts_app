package com.yigit.localdatabaselab

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class RvViewHolder(itemView : View, itemClick : (position : Int) -> Unit) : RecyclerView.ViewHolder(itemView)
{
    var tvNameSurname : TextView
    var tvEmail : TextView

    init {
        tvNameSurname = itemView.findViewById(R.id.tvNameSurname)
        tvEmail = itemView.findViewById(R.id.tvEMail)
        itemView.setOnClickListener {view ->
            itemClick(adapterPosition)
        }
    }
}