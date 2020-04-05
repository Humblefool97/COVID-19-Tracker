package com.tbs.covidtracker.model

import com.google.gson.annotations.SerializedName

data class CountryInfo(
    @SerializedName("_id")
    var id: String = "",
    @SerializedName("flag")
    var flagUrl: String = ""
)