package com.tbs.covidtracker.ui.allcases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tbs.covidtracker.AllCasesRepository
import com.tbs.covidtracker.model.AllCasesResponse
import com.tbs.covidtracker.state.State
import timber.log.Timber
import javax.inject.Inject

class AllCasesViewModel @Inject constructor(private val repository: AllCasesRepository) :
    ViewModel() {

    fun fetchAllCases(): LiveData<State<AllCasesResponse>> {
        val allCasesState = MutableLiveData<State<AllCasesResponse>>()
        val liveData = repository.getAllCases()
        liveData.observeForever { allCasesResponse ->
            when {
                allCasesResponse.isSuccess -> {
                    allCasesState.value = State.Success<AllCasesResponse>(allCasesResponse)
                }
                else -> {
                    //TODO:Handle Failure(no internet )
                    Timber.tag("LOCAL").e("Some error while fetching cases")

                }
            }
        }

        return allCasesState
    }
}