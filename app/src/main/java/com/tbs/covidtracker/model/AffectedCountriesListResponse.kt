package com.tbs.covidtracker.model

data class AffectedCountriesListResponse(
    var affectedCountryListResponse: List<AffectedCountryResponse> = emptyList()
) : BaseResponse()