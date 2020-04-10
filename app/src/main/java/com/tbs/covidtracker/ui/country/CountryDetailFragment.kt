package com.tbs.covidtracker.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.tbs.covidtracker.HomeActivity
import com.tbs.covidtracker.R
import com.tbs.covidtracker.model.AffectedCountryResponse
import com.tbs.covidtracker.ui.allcases.DetailsFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_detail.*

class CountryDetailFragment : DaggerFragment() {

    lateinit var affectedCountryResponse: AffectedCountryResponse
    private var detailFragment: DetailsFragment = DetailsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get data from previous fragment
        arguments?.get(DetailsFragment.BUNDLE_DATA_COUNTRY)?.let { response ->
            if (response is AffectedCountryResponse) {
                affectedCountryResponse = response
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (::affectedCountryResponse.isInitialized) {
            with(affectedCountryResponse) {
                activity?.let {
                    Glide
                        .with(it)
                        .load(countryInfo?.flagUrl)
                        .centerCrop()
                        .into(detailFlagImageView)

                    detailCountryNameTextView.text = country
                    //Replace the fragment
                    val allCasesResponse = affectedCountryResponse
                    DetailsFragment.replaceFragment(
                        detailFragment
                        , R.id.detailContainerView
                        , childFragmentManager
                        , allCasesResponse
                    )
                }
            }
        }
        (activity as HomeActivity).bottomNavView.visibility = View.GONE
    }

    companion object {
        const val TAG = "CountryDetailFragment"

        fun instantiate(
            containerId: Int
            , fragment: CountryDetailFragment
            , fragmentManager: FragmentManager
            , affectedCountryResponse: AffectedCountryResponse
        ) {
            val bundle = bundleOf(DetailsFragment.BUNDLE_DATA_COUNTRY to affectedCountryResponse)
            fragment.arguments = bundle
            fragmentManager
                .beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}