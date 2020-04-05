package com.tbs.covidtracker.ui.countries

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tbs.covidtracker.R
import com.tbs.covidtracker.state.State
import com.tbs.covidtracker.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_all_countries.*
import kotlinx.android.synthetic.main.layout_progress.*
import javax.inject.Inject

class AffectedCountriesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var affectedCountriesViewModel: AffectedCountriesViewModel
    lateinit var recyclerViewAdapter: CountriesListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
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
        recyclerViewAdapter = CountriesListAdapter(
            activity as Context,
            emptyList()
        )
        with(countriesRecyclerView) {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context)
        }
        affectedCountriesViewModel.fetchAffectedCountries().observe(this, Observer { state ->
            when (state) {
                is State.Success -> {
                    progressContainer.visibility = View.GONE
                    state.data?.let {
                        if (it.isNotEmpty()) {
                            recyclerViewAdapter.setData(it)
                        }
                    }
                }
            }
        })
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
}
