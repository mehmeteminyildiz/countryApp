package com.waterbase.from.ulkeleruygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waterbase.from.ulkeleruygulamasi.model.Country

class FeedViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()    // country listesini tutacağız
    val countryError = MutableLiveData<Boolean>()       // error var mı yok mu kontrolü
    val countryLoading = MutableLiveData<Boolean>()     // yükleniyor mu yüklendi mi kontrolü için
    // MutableLiveData: Değişebilir Live Data demektir

    fun refreshData() {
        val country1 = Country("Turkey", "Ankara", "Asia", "Turkish Lira", "Turkish", "")
        val country2 = Country("USA", "Washington", "America", "Dollar", "English", "")
        val country3 = Country("UK", "London", "Europe", "Sterlin", "English", "")

        val countryList = ArrayList<Country>()
        countryList.add(country1)
        countryList.add(country2)
        countryList.add(country3)

        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }
}