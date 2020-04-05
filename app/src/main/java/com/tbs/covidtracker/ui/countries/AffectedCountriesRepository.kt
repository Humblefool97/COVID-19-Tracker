package com.tbs.covidtracker.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tbs.covidtracker.model.AffectedCountriesListResponse
import com.tbs.covidtracker.model.AllCasesResponse
import com.tbs.covidtracker.network.COVIDApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class AffectedCountriesRepository(val covidApiService: COVIDApiService) {

    fun getAffectedCountries(): LiveData<AffectedCountriesListResponse> {
        val affectedCountriedLiveData = MutableLiveData<AffectedCountriesListResponse>()
        GlobalScope.launch {
            covidApiService.getAllCountriesData().enqueue(object :
                Callback<AffectedCountriesListResponse> {
                override fun onFailure(call: Call<AffectedCountriesListResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<AffectedCountriesListResponse>,
                    response: Response<AffectedCountriesListResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() as AffectedCountriesListResponse
                        affectedCountriedLiveData.value = body
                    } else {
                        Timber.tag("LOCAL").e("Some error while fetching cases")
                    }
                }

            })
        }
        return affectedCountriedLiveData
    }
}