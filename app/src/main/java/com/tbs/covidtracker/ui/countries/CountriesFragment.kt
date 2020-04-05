package com.tbs.covidtracker.ui.countries

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tbs.covidtracker.ui.allcases.AllCasesFragment

class CountriesFragment : Fragment() {


    companion object {
        const val TAG = "CountriesFragment"
        fun instantiateFragment(
            allCasesFragment: CountriesFragment,
            containerId: Int,
            fragmentManager: FragmentManager
        ) {
            fragmentManager
                .beginTransaction()
                .replace(containerId, allCasesFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
