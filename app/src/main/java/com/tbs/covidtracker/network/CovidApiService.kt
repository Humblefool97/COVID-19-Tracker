package com.tbs.covidtracker.network

import com.tbs.covidtracker.model.AllCasesResponse
import retrofit2.Call
import retrofit2.http.GET

interface CovidApiService {
    @GET("all")
    fun getWorldData(): Call<AllCasesResponse>
}