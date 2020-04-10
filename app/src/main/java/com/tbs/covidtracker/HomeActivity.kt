package com.tbs.covidtracker

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import com.tbs.covidtracker.ui.allcases.DetailsFragment
import com.tbs.covidtracker.ui.countries.AffectedCountriesFragment
import com.tbs.covidtracker.util.isVisible
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber

class HomeActivity : DaggerAppCompatActivity() {
    private var detailsFragment: DetailsFragment = DetailsFragment()
    private var affectedCountriesFragment: AffectedCountriesFragment = AffectedCountriesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpToolbar()

        bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottomMenuWorld -> {
                    Timber.tag(TAG).d("bottomMenuWorld selected..")
                    DetailsFragment.replaceFragment(
                        detailsFragment,
                        R.id.fragmentContainer,
                        supportFragmentManager
                    )
                    true
                }
                R.id.bottomMenuCountries -> {
                    Timber.tag(TAG).d("bottomMenuCountries selected...")
                    AffectedCountriesFragment.instantiateFragment(
                        affectedCountriesFragment,
                        R.id.fragmentContainer,
                        supportFragmentManager
                    )
                    true
                }
                else -> {
                    false
                }
            }
        }

        bottomNavView.selectedItemId = R.id.bottomMenuWorld
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.let {
            it.title = getString(R.string.app_name)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    override fun onBackPressed() {
        if(!bottomNavView.isVisible())
             bottomNavView.visibility = View.VISIBLE
        super.onBackPressed()
    }

    companion object {
        private const val TAG = "HomeActivity"
    }
}