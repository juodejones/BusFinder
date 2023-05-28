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
import com.jones.busfinder.R
import com.jones.busfinder.data.Bus

class StopsRvAdapter(
    private val context: Context,
    private val locList: HashMap<String, Double>? = null,
) : RecyclerView.Adapter<StopsRvAdapter.ViewHolder>() {

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
        if (locList != null) return locList.size
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageDrawable(locDrawable)
        Log.d("MyRecyclerView", "onBindViewHolder: ${locList?.size}")
        val item = locList?.keys?.toList()?.get(position)
        holder.busTextView.text = buildString {
            append(item)
            append(" --  $")
            append(locList?.get(item))
        }
    }

    interface BusClickListener {
        fun onBusClick(bus: Bus)
    }

}