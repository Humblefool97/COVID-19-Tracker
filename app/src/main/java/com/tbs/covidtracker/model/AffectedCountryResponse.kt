package com.tbs.covidtracker.model

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AffectedCountryResponse(
    @SerializedName("country")
    var country: String = ""
) : AllCasesResponse()