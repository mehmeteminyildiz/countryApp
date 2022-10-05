package com.waterbase.from.ulkeleruygulamasi.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.waterbase.from.ulkeleruygulamasi.model.Country

@Dao
interface CountryDAO {
    // veritabanına ulaşmak istediğimiz metotları yazıyoruz.

    @Insert
    suspend fun insertAll(vararg countries: Country): List<Long>
    // suspend      -> coroutine içinde çalıştırabiliyoruz... pause & resume metotları da var
    // vararg       -> değişken sayıda country objesi verebiliyoruz
    // List<Long>   -> Model.kt'de oluşturduğumuz primary key (uuid) değerlerini dönüyor

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()

    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId ")
    suspend fun getCountry(countryId: Int): Country
}