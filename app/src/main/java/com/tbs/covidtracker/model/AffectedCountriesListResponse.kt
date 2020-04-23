package com.tbs.covidtracker.model

import android.annotation.SuppressLint

@SuppressLint("ParcelCreator")
data class AffectedCountriesListResponse(
    var affectedCountryListResponse: MutableList<AffectedCountryResponse> = mutableListOf()
) : BaseResponse()