package com.waterbase.from.ulkeleruygulamasi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String?,

    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    var capital: String?,

    @ColumnInfo(name = "region")
    @SerializedName("region")
    var region: String?,

    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    var currency: String?,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    var language: String?,

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    var imageURL: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}