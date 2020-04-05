package com.tbs.covidtracker.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.tbs.covidtracker.model.AffectedCountriesListResponse
import com.tbs.covidtracker.model.AffectedCountryResponse
import com.tbs.covidtracker.state.State
import timber.log.Timber
import javax.inject.Inject

class AffectedCountriesViewModel @Inject constructor(var repository: AffectedCountriesRepository) :
    ViewModel() {

    fun fetchAffectedCountries(): LiveData<State<List<AffectedCountryResponse>>> {
        val affectedCountriesLiveData = MutableLiveData<State<List<AffectedCountryResponse>>>()
        val liveData = repository.getAffectedCountries()
        liveData.observeForever(Observer {
            when {
                it.isNotEmpty() -> {
                    affectedCountriesLiveData.value =
                        State.Success(it)
                }
                else -> {
                    //TODO:Handle Failure(no internet )
                    Timber.tag("LOCAL").e("Some error while fetching country list")
                }

            }
        })
        return affectedCountriesLiveData
    }
}