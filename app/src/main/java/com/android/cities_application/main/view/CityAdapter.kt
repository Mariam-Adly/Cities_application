package com.android.cities_application.main.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.cities_application.R
import com.android.cities_application.main.model.City

class CityAdapter (private val context: Context): ListAdapter<City, CityAdapter.ViewHolder>(
    MyDifUnit()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item,parent,false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.item.setOnClickListener {
            val bundle = Bundle().apply {
                putFloat("lat", getItem(position).coord.lat.toFloat())
                putFloat("lon", getItem(position).coord.lon.toFloat())
                putString("cityName" , getItem(position).name)

            }
            val navController = Navigation.findNavController(context as Activity, R.id.nav_host_fragment)
            navController.navigate(R.id.action_mainFragment_to_mapFragment, bundle)
        }

        holder.cityTV.text = "${getItem(position).name}, ${getItem(position).country}"
        holder.coorTV.text = "lat:${getItem(position).coord.lat}, lon:${getItem(position).coord.lon}"

    }

    inner class ViewHolder (private val itemView: View): RecyclerView.ViewHolder(itemView){

       val item : ConstraintLayout
           get() = itemView.findViewById(R.id.item)
        val cityTV : TextView
            get() = itemView.findViewById(R.id.city_name)

        val coorTV : TextView
            get() = itemView.findViewById(R.id.coordinator)

    }
}


class MyDifUnit: DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }
}