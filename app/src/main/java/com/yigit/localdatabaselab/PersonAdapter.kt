package com.yigit.localdatabaselab

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class PersonAdapter(var alList : ArrayList<Person>, itemClick : (position : Int) -> Unit) : RecyclerView.Adapter<RvViewHolder>() {

    private val itemClick = itemClick

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RvViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.list_item, p0, false)
        return RvViewHolder(v, itemClick)
    }

    override fun onBindViewHolder(p0: RvViewHolder, p1: Int) {
        p0.tvNameSurname.text = alList[p1].name + " " + alList[p1].surname
        p0.tvEmail.text = alList[p1].email
    }

    override fun getItemCount(): Int {
        return alList.size
    }
}