package com.waterbase.from.ulkeleruygulamasi.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    var name: String?,
    @SerializedName("capital")
    var capital: String?,
    @SerializedName("region")
    var region: String?,
    @SerializedName("currency")
    var currency: String?,
    @SerializedName("language")
    var language: String?,
    @SerializedName("flag")
    var imageURL: String?
)