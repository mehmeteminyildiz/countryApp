package com.waterbase.from.ulkeleruygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.waterbase.from.ulkeleruygulamasi.model.Country
import com.waterbase.from.ulkeleruygulamasi.room.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : BaseViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }
    }

}