package com.waterbase.from.ulkeleruygulamasi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.waterbase.from.ulkeleruygulamasi.model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDAO

    // singleton
    // static olmasını ve başka sayfalardan da ulaşabilmemizi sağlıyor
    companion object {
        // volatile: farklı thread'lere de görünür olmasını sağlıyor

        @Volatile
        private var instance: CountryDatabase? = null // oluşturulacak tek objemiz bu olacak

        private val lock = Any() // kilitlenme kontrolü yapan değişken.. herhangi bir şey olabilir.

        // invoke: başlatmak... instance var mı onu kontrol eder; varsa onu döner, yoksa oluşturmak için kullanılır
        // synchronized: ayn anda tek bir thread'in erişmesini sağlar
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                // also: makeDatabase()'den sonra:
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, CountryDatabase::class.java, "countrydatabase"
        ).build()


    }

}