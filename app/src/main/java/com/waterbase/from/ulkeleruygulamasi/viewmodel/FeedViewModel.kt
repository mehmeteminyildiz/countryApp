package com.waterbase.from.ulkeleruygulamasi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.waterbase.from.ulkeleruygulamasi.model.Country
import com.waterbase.from.ulkeleruygulamasi.room.CountryDatabase
import com.waterbase.from.ulkeleruygulamasi.service.CountryAPIService
import com.waterbase.from.ulkeleruygulamasi.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()

    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 0.1 * 60 * 1000 * 1000 * 1000L

    val countries = MutableLiveData<List<Country>>()    // country listesini tutacağız
    val countryError = MutableLiveData<Boolean>()       // error var mı yok mu kontrolü
    val countryLoading = MutableLiveData<Boolean>()     // yükleniyor mu yüklendi mi kontrolü için
    // MutableLiveData: Değişebilir Live Data demektir

    fun refreshData() {
        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()
        } else {

            getDataFromAPI()
        }
    }

    fun refreshFromAPI() {
        getDataFromAPI()
    }

    private fun getDataFromSQLite() {
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(), "Countries from SQLite", Toast.LENGTH_SHORT).show()
        }
    }

    fun getDataFromAPI() {
        countryLoading.value = true

        disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(), "Countries from API", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onError(e: Throwable) {
                        countryError.value = true
                        countryLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun storeInSQLite(list: List<Country>) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            var longList = dao.insertAll(*list.toTypedArray()) // elemanları tek tek hale getiriyor.

            var i = 0
            while (i < list.size) {
                list[i].uuid = longList[i].toInt()
                i++
            }
            showCountries(list)
        }
        customPreferences.saveTime(System.nanoTime())
    }

    private fun showCountries(countryList: List<Country>) {
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}