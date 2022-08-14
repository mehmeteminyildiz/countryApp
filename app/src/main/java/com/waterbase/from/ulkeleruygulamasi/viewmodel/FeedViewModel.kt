package com.waterbase.from.ulkeleruygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waterbase.from.ulkeleruygulamasi.model.Country
import com.waterbase.from.ulkeleruygulamasi.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {

    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()    // country listesini tutacağız
    val countryError = MutableLiveData<Boolean>()       // error var mı yok mu kontrolü
    val countryLoading = MutableLiveData<Boolean>()     // yükleniyor mu yüklendi mi kontrolü için
    // MutableLiveData: Değişebilir Live Data demektir

    fun refreshData() {
        getDataFromAPI()
    }

    fun getDataFromAPI() {
        countryLoading.value = true

        disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        countries.value = t
                        countryError.value = false
                        countryLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        countryError.value = true
                        countryLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }
}