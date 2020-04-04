package com.tbs.covidtracker.ui.allcases

import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AllCasesFragment:DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var allCasesViewModel: AllCasesViewModel


}