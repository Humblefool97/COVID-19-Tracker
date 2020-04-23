package com.tbs.covidtracker.ui.allcases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tbs.covidtracker.R
import com.tbs.covidtracker.model.AffectedCountryResponse
import com.tbs.covidtracker.model.AllCasesResponse
import com.tbs.covidtracker.state.State
import com.tbs.covidtracker.util.Utilities
import com.tbs.covidtracker.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_worldwide.*
import kotlinx.android.synthetic.main.layout_cardview_horizontal.view.*
import kotlinx.android.synthetic.main.layout_cardview_regular.view.*
import kotlinx.android.synthetic.main.layout_progress.*
import javax.inject.Inject

@Suppress("IMPLICIT_CAST_TO_ANY")
class DetailsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var allCasesViewModel: AllCasesViewModel
    lateinit var allCasesResponse: AllCasesResponse
    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.get(BUNDLE_DATA_COUNTRY)?.let { response ->
            if (response is AllCasesResponse) {
                allCasesResponse = response
            }
        }
        allCasesViewModel = viewModelProvider(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_worldwide, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (::allCasesResponse.isInitialized) {
            progressContainer.visibility = View.GONE
            allCasesResponse.setViews()
        } else {
            allCasesViewModel.fetchAllCases().observe(this, Observer { state ->
                when (state) {
                    is State.Success -> {
                        progressContainer.visibility = View.GONE
                        val allCasesResponse = state.data as AllCasesResponse
                        with(allCasesResponse) {
                            //Set Total cases
                            setViews()
                        }
                    }
                    else -> {
                        progressContainer.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    private fun AllCasesResponse.setViews() {
        setTotalCases()
        //Set Death cases
        setTotalDeaths()
        //Set Critical
        setCriticalCases()
        //Set Active
        setActiveCases()
        //Set Recovered
        setCoveredCases()
        //Set Tests
        setTests()
    }

    private fun AllCasesResponse.setTests() {
        testCardView.cardRegularImageView.setImageResource(R.drawable.ivc_tests)
        testCardView.cardRegularTitleText.run {
            text = "Tests"
            setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }
        testCardView.cardRegularSubTitleText.run {
            text = Utilities.getFormattedNumber(tests)
            setTextColor(resources.getColor(R.color.colorPrimaryDark))
        }
    }

    private fun AllCasesResponse.setCoveredCases() {
        recoveredCardView.cardRegularImageView.setImageResource(R.drawable.vc_recovery)
        recoveredCardView.cardRegularTitleText.run {
            text = getString(R.string.tile_recovered)
            setTextColor(resources.getColor(R.color.colorHope))
        }
        recoveredCardView.cardRegularSubTitleText.run {
            text = Utilities.getFormattedNumber(totalRecovered)
            setTextColor(resources.getColor(R.color.colorHope))
        }
    }

    private fun AllCasesResponse.setActiveCases() {
        ActiveCardView.cardRegularImageView.setImageResource(R.drawable.vc_active_cases)
        ActiveCardView.cardRegularTitleText.run {
            text = getString(R.string.tile_active)
            setTextColor(resources.getColor(R.color.color_danger))
        }
        ActiveCardView.cardRegularSubTitleText.run {
            text = Utilities.getFormattedNumber(totalActive)
            setTextColor(resources.getColor(R.color.color_danger))
        }
    }

    private fun AllCasesResponse.setCriticalCases() {
        criticalCardView.cardRegularImageView.setImageResource(R.drawable.vc_critical)
        criticalCardView.cardRegularTitleText.run {
            text = "Critical"
            setTextColor(resources.getColor(android.R.color.black))
        }
        criticalCardView.cardRegularSubTitleText.run {
            text = critical
            setTextColor(resources.getColor(android.R.color.black))
        }
    }

    private fun AllCasesResponse.setTotalDeaths() {
        totalDeathsCardView.itemImageView.setImageResource(R.drawable.vc_death)
        totalDeathsCardView.itemTitleTextView.run {
            text = resources.getString(R.string.tile_death)
            setTextColor(resources.getColor(android.R.color.black))
        }
        totalDeathsCardView.itemSubtitleTextView.run {
            text = Utilities.getFormattedNumber(totalDeaths)
            setTextColor(resources.getColor(android.R.color.black))
        }
        totalDeathsCardView.itemTodayCountTextView.run {
            setTextColor(resources.getColor(android.R.color.black))
            val formattedString = getString(R.string.label_plus_cases,Utilities.getFormattedNumber(todayDeaths))
            text = formattedString
        }
    }

    private fun AllCasesResponse.setTotalCases() {
        totalCasesCardView.itemImageView.setImageResource(R.drawable.vc_cases)
        totalCasesCardView.itemTitleTextView.run {
            text = resources.getString(R.string.tile_cases)
            setTextColor(resources.getColor(R.color.color_danger))
        }
        totalCasesCardView.itemSubtitleTextView.run {
            text = Utilities.getFormattedNumber(totalCases)
            setTextColor(resources.getColor(R.color.color_danger))
        }
        totalCasesCardView.itemTodayCountTextView.run {
            setTextColor(resources.getColor(R.color.color_danger))
            val formattedString = getString(R.string.label_plus_cases,Utilities.getFormattedNumber(todayCases))
            text = formattedString
        }
    }

    companion object {
        const val TAG = "AllCasesFragment"
        const val BUNDLE_DATA_COUNTRY = "BUNDLE_DATA_COUNTRY"

        fun replaceFragment(
            allCasesFragment: DetailsFragment,
            containerId: Int,
            fragmentManager: FragmentManager,
            data: AllCasesResponse? = null
        ) {
            data?.let {
                allCasesFragment.arguments = bundleOf(BUNDLE_DATA_COUNTRY to data)
            }
            fragmentManager
                .beginTransaction()
                .replace(containerId, allCasesFragment)
                .commit()
        }
    }
}