package com.tbs.covidtracker.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryInfo(
    @SerializedName("_id")
    var id: String = "",
    @SerializedName("flag")
    var flagUrl: String = ""
):Parcelable