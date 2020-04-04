package com.tbs.covidtracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tbs.covidtracker.model.AllCasesResponse
import com.tbs.covidtracker.network.CovidApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AllCasesRepository @Inject constructor(private val apiService: CovidApiService) {

    fun getAllCases(): LiveData<AllCasesResponse> {
        val data = MutableLiveData<AllCasesResponse>()
        apiService.getWorldData().enqueue(object : Callback<AllCasesResponse> {

            override fun onResponse(
                call: Call<AllCasesResponse>,
                response: Response<AllCasesResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() as AllCasesResponse
                    data.value = body
                } else {
                    //TODO:Implement this
                }
            }

            override fun onFailure(call: Call<AllCasesResponse>, t: Throwable) {
                //TODO:Implement this.Detect no network using a interceptor
            }

        })
        return data
    }
}