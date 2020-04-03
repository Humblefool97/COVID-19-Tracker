package com.tbs.covidtracker.model

import com.google.gson.annotations.SerializedName

data class AllCases(
    @SerializedName("cases")
    var totalCases: String = "",
    @SerializedName("deaths")
    var totalDeaths: String = "",
    @SerializedName("recovered")
    var totalRecovered: String = "",
    @SerializedName("updated")
    var lastUpdated: String = "",
    @SerializedName("active")
    var totalActive: String = "",
    @SerializedName("affectedCountries")
    var totalNumberOfAffectedCountries: String = ""
)