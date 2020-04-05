package com.tbs.covidtracker.ui.countries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tbs.covidtracker.R
import com.tbs.covidtracker.model.AffectedCountryResponse
import kotlinx.android.synthetic.main.item_countries_list.view.*

class CountriesListAdapter(
    private val context: Context,
    var affectedCountriesList: ArrayList<AffectedCountryResponse>
) :
    RecyclerView.Adapter<CountriesListAdapter.CountriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_countries_list, parent, false)
        return CountriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = affectedCountriesList.size

    fun setData(newList: ArrayList<AffectedCountryResponse>) {
        affectedCountriesList = newList
        notifyDataSetChanged()
    }

    inner class CountriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val countriesIv: ImageView = view.countryImageView
        private val countryTv: TextView = view.countryTextView
        private val totalValTv: TextView = view.totalValTextView
        private val activeValTv: TextView = view.activeValTextView
        private val deathTv: TextView = view.deathValTextView

        fun bind(position: Int) {
            val affectedCountry = affectedCountriesList[position]
            with(affectedCountry) {
                //Set Image
                Glide
                    .with(context)
                    .load(countryInfo?.flagUrl)
                    .centerCrop()
                    .into(countriesIv)
                countryTv.text = country
                totalValTv.text = cases
                activeValTv.text = active
                deathTv.text = deaths
            }
        }
    }
}