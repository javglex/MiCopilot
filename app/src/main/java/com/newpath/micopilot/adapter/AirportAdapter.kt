package com.newpath.micopilot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.newpath.micopilot.R
import com.newpath.micopilot.network.TafDataModel
import com.newpath.micopilot.ui.viewholder.AirportItemViewHolder
import kotlinx.android.synthetic.main.nav_header_main.view.*

class AirportAdapter: RecyclerView.Adapter<AirportItemViewHolder>() {

    var airportList =  listOf<TafDataModel>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirportItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.airport_item, parent, false) as ConstraintLayout

        return  AirportItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: AirportItemViewHolder, position: Int) {

        val airportItem = airportList[position]

        if (airportItem.stationId!=null)
            (holder.layout.getViewById(R.id.tv_airport_id) as TextView).text = airportItem.stationId
        else (holder.layout.getViewById(R.id.tv_airport_id) as TextView).text = "Airport ID not found!"

    }

    override fun getItemCount(): Int = airportList.size


}