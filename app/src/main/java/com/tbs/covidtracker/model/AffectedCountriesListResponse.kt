package com.tbs.covidtracker.model

data class AffectedCountriesListResponse(
    var affectedCountryListResponse: MutableList<AffectedCountryResponse> = mutableListOf()
) : BaseResponse()