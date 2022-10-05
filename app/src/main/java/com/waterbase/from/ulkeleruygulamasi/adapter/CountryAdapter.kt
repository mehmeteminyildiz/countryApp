package com.waterbase.from.ulkeleruygulamasi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.waterbase.from.ulkeleruygulamasi.databinding.ItemCountryBinding
import com.waterbase.from.ulkeleruygulamasi.model.Country
import com.waterbase.from.ulkeleruygulamasi.util.placeholderProgressBar
import com.waterbase.from.ulkeleruygulamasi.util.setImageWithURL
import com.waterbase.from.ulkeleruygulamasi.view.FeedFragmentDirections

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
            imgFlag.setImageWithURL(
                list[position].imageURL,
                placeholderProgressBar(holder.itemView.context)
            )
        }
        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryUuid = list[position].uuid)
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