package com.tbs.covidtracker.model

import com.google.gson.annotations.SerializedName

/**
 *
 */
data class AllCasesResponse(
    @SerializedName("cases")
    var totalCases: String = "",
    @SerializedName("todayCases")
    var todayCases: String = "",
    @SerializedName("deaths")
    var totalDeaths: String = "",
    @SerializedName("todayDeaths")
    var todayDeaths: String = "",
    @SerializedName("recovered")
    var totalRecovered: String = "",
    @SerializedName("updated")
    var lastUpdated: String = "",
    @SerializedName("active")
    var totalActive: String = "",
    @SerializedName("critical")
    var critical: String = "",
    @SerializedName("tests")
    var tests: String = "",
    @SerializedName("affectedCountries")
    var totalNumberOfAffectedCountries: String = ""
) : BaseResponse()