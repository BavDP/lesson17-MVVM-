package com.example.lesson17.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lesson17.R
import com.example.lesson17.models.Country

class CountriesAdapter(private val countries: List<Country>?): RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(country: Country?) {
            val countryTextView = view.findViewById<TextView>(R.id.coutryNameTextView)
            val flagImage = view.findViewById<ImageView>(R.id.flagImage)
            countryTextView.text = country?.name?:""
            Glide.with(view)
                .load(country?.flag)
                .into(flagImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coutry_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries?.let { it[position] })
    }

    override fun getItemCount(): Int {
        return countries?.size ?:0
    }
}