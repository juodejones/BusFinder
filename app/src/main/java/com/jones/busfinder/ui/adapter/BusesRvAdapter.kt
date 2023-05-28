package com.jones.busfinder.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.jones.busfinder.data.Bus
import com.jones.busfinder.R

class BusesRvAdapter(
    private val context: Context,
    private val isBus: Boolean,
    private val busList: List<Bus?>? = null,
    private val locList: HashMap<String, Double>? = null,
    private val busClickListener: BusClickListener? = null
) : RecyclerView.Adapter<BusesRvAdapter.ViewHolder>() {

    private val busDrawable =
        AppCompatResources.getDrawable(context, R.drawable.baseline_directions_bus_24)
    private val locDrawable =
        AppCompatResources.getDrawable(context, R.drawable.baseline_location_on_24)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val busTextView: TextView = itemView.findViewById(R.id.bus_id_tv)
        val imageView: ImageView = itemView.findViewById(R.id.rv_imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rv_buses, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (isBus && busList != null) return busList.size
        else if(!isBus && locList != null ) return locList.size
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (isBus) {
            holder.imageView.setImageDrawable(busDrawable)
            holder.busTextView.text = busList?.get(position)!!.id
            if (busClickListener != null) {
                holder.busTextView.setOnClickListener {
                    busClickListener.onBusClick(busList[position]!!)
                }
            }
        } else {
            holder.imageView.setImageDrawable(locDrawable)
            Log.d("MyRecyclerView", "onBindViewHolder: ${locList?.size}")
            val item = locList?.keys?.toList()?.get(position)
            holder.busTextView.text = buildString {
                append(item)
                append(" --  $")
                append(locList?.get(item))
            }
        }
    }

    interface BusClickListener {
        fun onBusClick(bus: Bus)
    }

}