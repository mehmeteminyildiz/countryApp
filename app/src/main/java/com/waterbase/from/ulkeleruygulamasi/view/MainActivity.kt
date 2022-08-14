package com.waterbase.from.ulkeleruygulamasi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.waterbase.from.ulkeleruygulamasi.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

        /*
        viewModel'de arkaplan işlemlerini yapacağız.
        arayüz ile ilgili işleri view'de yapacağız
        tüm bunlar model'den beslenecek
        */
    }
}