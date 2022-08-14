package com.waterbase.from.ulkeleruygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waterbase.from.ulkeleruygulamasi.model.Country

class CountryViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom() {
        val country = Country("Turkey", "Ankara", "Asia", "Turkish Lira", "Turkish", "")
        countryLiveData.value = country
    }
}