package com.tbs.covidtracker

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber

class HomeActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        bottomNavView.selectedItemId = R.id.bottomMenuWorld
        bottomNavView.setOnNavigationItemReselectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottomMenuWorld -> {
                    Timber.tag(TAG).d("bottomMenuWorld selected..")
                }
                R.id.bottomMenuCountries -> {
                    Timber.tag(TAG).d("bottomMenuCountries selected...")
                }
            }
        }
    }

    companion object {
        private const val TAG = "HomeActivity"
    }
}