package com.tbs.covidtracker

import android.os.Bundle
import com.tbs.covidtracker.ui.allcases.AllCasesFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpToolbar()
        bottomNavView.setOnNavigationItemReselectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottomMenuWorld -> {
                    Timber.tag(TAG).d("bottomMenuWorld selected..")
                    AllCasesFragment.replaceFragment(
                        AllCasesFragment(),
                        R.id.fragmentContainer,
                        supportFragmentManager
                    )
                }
                R.id.bottomMenuCountries -> {
                    Timber.tag(TAG).d("bottomMenuCountries selected...")
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

    companion object {
        private const val TAG = "HomeActivity"
    }
}