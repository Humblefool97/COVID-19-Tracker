package com.tbs.covidtracker.ui.countries

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tbs.covidtracker.R
import com.tbs.covidtracker.model.AffectedCountryResponse
import com.tbs.covidtracker.state.State
import com.tbs.covidtracker.ui.country.CountryDetailFragment
import com.tbs.covidtracker.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_all_countries.*
import kotlinx.android.synthetic.main.layout_progress.*
import javax.inject.Inject

class AffectedCountriesFragment : DaggerFragment()
    , SearchView.OnQueryTextListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var affectedCountriesViewModel: AffectedCountriesViewModel
    lateinit var recyclerViewAdapter: CountriesListAdapter
    private var affectedCountriesList: List<AffectedCountryResponse> = mutableListOf()
    private var searchList: List<AffectedCountryResponse> = affectedCountriesList
    private var countryDetailFragment: CountryDetailFragment = CountryDetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        affectedCountriesViewModel = viewModelProvider(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setUpSearchView()
        swipeRefreshLayout.setOnRefreshListener {
            fetchData()
        }
        fetchData()
    }

    private fun fetchData() {
        progressContainer.visibility = View.VISIBLE

        affectedCountriesViewModel.fetchAffectedCountries().observe(this, Observer { state ->
            with(swipeRefreshLayout) {
                if (isRefreshing)
                    isRefreshing = false
            }
            when (state) {
                is State.Success -> {
                    progressContainer.visibility = View.GONE
                    state.data?.let { response ->
                        affectedCountriesList = response
                        searchList = affectedCountriesList
                        if (response.isNotEmpty()) {
                            recyclerViewAdapter.setData(response)
                        }
                    }
                }
            }
        })
    }

    private fun setUpSearchView() {
        searchView.setOnQueryTextListener(this)
    }

    private fun setupRecyclerView() {
        recyclerViewAdapter = CountriesListAdapter(
            activity as Context,
            emptyList()
        ) { position ->
            val affectedCountryResponse = searchList[position]
            //TODO:See how fragment manager is handled in iosched
            fragmentManager?.let {
                CountryDetailFragment.instantiate(
                    R.id.fragmentContainer,
                    countryDetailFragment,
                    it, affectedCountryResponse
                )
            }
        }
        with(countriesRecyclerView) {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


    companion object {
        const val TAG = "CountriesFragment"
        fun instantiateFragment(
            allCasesFragment: AffectedCountriesFragment,
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchList = affectedCountriesList.filter {
            it.country.contains(newText.toString(), true)
        }
        recyclerViewAdapter.setData(searchList)
        return true
    }
}
