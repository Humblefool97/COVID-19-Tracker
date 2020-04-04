package com.tbs.covidtracker.ui.launch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LauncherViewModel @Inject constructor() : ViewModel() {
    val liveData: LiveData<LaunchState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<LaunchState>()

    init {
        GlobalScope.launch {
            delay(5 * 1000)
            mutableLiveData.postValue(LaunchState.GoToMain())
        }
    }

    sealed class LaunchState {
        class GoToMain : LaunchState()
    }
}