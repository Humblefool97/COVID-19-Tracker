package com.tbs.covidtracker.ui.allcases

import androidx.lifecycle.*
import com.tbs.covidtracker.AllCasesRepository
import com.tbs.covidtracker.state.State
import com.tbs.covidtracker.model.AllCasesResponse
import javax.inject.Inject

class AllCasesViewModel @Inject constructor(val repository: AllCasesRepository) : ViewModel() {

    fun fetchAllCases(): LiveData<State<AllCasesResponse>> {
        val allCasesState = MutableLiveData<State<AllCasesResponse>>()
        val allCasesLiveData = repository.getAllCases()
        MediatorLiveData<AllCasesResponse>().addSource(
            allCasesLiveData,
            Observer { allCasesResponse ->
                when {
                    allCasesResponse.isSuccess -> {
                        allCasesState.value = State.Success<AllCasesResponse>(allCasesResponse)
                    }
                    else -> {
                        //TODO:Handle Failure(no internet )
                    }
                }
            })
        return allCasesState
    }
}