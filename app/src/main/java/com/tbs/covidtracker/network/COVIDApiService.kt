package com.tbs.covidtracker.network

import com.tbs.covidtracker.model.AffectedCountryResponse
import com.tbs.covidtracker.model.AllCasesResponse
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface COVIDApiService {
    @GET("all")
    fun getWorldData(): Call<AllCasesResponse>

    @GET("countries")
    fun getAllCountriesData(@QueryMap params:Map<String,String>): Call<List<AffectedCountryResponse>>
}