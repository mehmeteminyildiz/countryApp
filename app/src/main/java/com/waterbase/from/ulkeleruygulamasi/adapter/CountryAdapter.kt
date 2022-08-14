package com.waterbase.from.ulkeleruygulamasi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.waterbase.from.ulkeleruygulamasi.databinding.ItemCountryBinding
import com.waterbase.from.ulkeleruygulamasi.model.Country
import com.waterbase.from.ulkeleruygulamasi.view.FeedFragmentDirections
import timber.log.Timber

class CountryAdapter() :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var _list = ArrayList<Country>()
    val list get() = _list.toList()
    private lateinit var context: Context


    class CountryViewHolder(val bindingCountryViewHolder: ItemCountryBinding) :
        RecyclerView.ViewHolder(bindingCountryViewHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        context = parent.context

        return CountryViewHolder(
            ItemCountryBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindingCountryViewHolder.apply {
            //imgFlag   tvCountryName   tvRegion
            tvCountryName.text = list[position].name
            tvRegion.text = list[position].region
        }
        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateCountryList(newCountryList: List<Country>) {
        _list.clear()
        _list.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size
}