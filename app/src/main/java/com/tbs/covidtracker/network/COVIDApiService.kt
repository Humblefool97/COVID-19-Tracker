package com.tbs.covidtracker.network

import com.tbs.covidtracker.model.AffectedCountryResponse
import com.tbs.covidtracker.model.AllCasesResponse
import retrofit2.Call
import retrofit2.http.GET

interface COVIDApiService {
    @GET("all")
    fun getWorldData(): Call<AllCasesResponse>

    @GET("countries")
    fun getAllCountriesData(): Call<List<AffectedCountryResponse>>
}