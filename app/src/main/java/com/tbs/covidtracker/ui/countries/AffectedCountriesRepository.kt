package com.tbs.covidtracker.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tbs.covidtracker.model.AffectedCountriesListResponse
import com.tbs.covidtracker.model.AffectedCountryResponse
import com.tbs.covidtracker.model.AllCasesResponse
import com.tbs.covidtracker.network.COVIDApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class AffectedCountriesRepository @Inject constructor(var covidApiService: COVIDApiService) {

    fun getAffectedCountries(): LiveData<List<AffectedCountryResponse>> {
        val affectedCountriedLiveData = MutableLiveData<List<AffectedCountryResponse>>()
        GlobalScope.launch {
            val map = hashMapOf("sort" to "cases")
            covidApiService.getAllCountriesData(map).enqueue(object :
                Callback<List<AffectedCountryResponse>> {
                override fun onFailure(call: Call<List<AffectedCountryResponse>>, t: Throwable) {
                    t.printStackTrace()
                    Timber.tag("LOCAL").e("Failure while fetching cases${t.localizedMessage}")
                }

                override fun onResponse(
                    call: Call<List<AffectedCountryResponse>>,
                    response: Response<List<AffectedCountryResponse>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() as List<AffectedCountryResponse>
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