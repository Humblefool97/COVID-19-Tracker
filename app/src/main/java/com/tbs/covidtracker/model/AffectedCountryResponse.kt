package com.tbs.covidtracker.model

import com.google.gson.annotations.SerializedName

data class AffectedCountryResponse(
    @SerializedName("country")
    var country: String = "",
    @SerializedName("countryInfo")
    var countryInfo: CountryInfo? = null,
    @SerializedName("cases")
    var cases: String = "",
    @SerializedName("todayCases")
    var todayCases: String = "",
    @SerializedName("deaths")
    var deaths: String = "",
    @SerializedName("todayDeaths")
    var todayDeaths: String = "",
    @SerializedName("recovered")
    var recovered: String = "",
    @SerializedName("active")
    var active: String = "",
    @SerializedName("critical")
    var critical: String = "",
    @SerializedName("casesPerOneMillion")
    var casesPerOneMillion: String = "",
    @SerializedName("deathsPerOneMillion")
    var deathsPerOneMillion: String = "",
    @SerializedName("updated")
    var updated: String = ""
) : BaseResponse()