package com.tbs.covidtracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tbs.covidtracker.model.AllCasesResponse
import com.tbs.covidtracker.network.COVIDApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class AllCasesRepository @Inject constructor(val apiService: COVIDApiService) {

    fun getAllCases(): LiveData<AllCasesResponse> {
        val data = MutableLiveData<AllCasesResponse>()
        GlobalScope.launch {
            apiService.getWorldData().enqueue(object : Callback<AllCasesResponse> {

                override fun onResponse(
                    call: Call<AllCasesResponse>,
                    response: Response<AllCasesResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() as AllCasesResponse
                        data.value = body
                    } else {
                        Timber.tag("LOCAL").e("Some error while fetching cases")
                    }
                }

                override fun onFailure(call: Call<AllCasesResponse>, t: Throwable) {
                    //TODO:Implement this.Detect no network using a interceptor
                    Timber.tag("LOCAL").e("Some error while fetching cases..:${t.localizedMessage}")
                }

            })
        }
        return data
    }
}