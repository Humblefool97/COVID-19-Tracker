package com.tbs.covidtracker.ui.launch

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tbs.covidtracker.HomeActivity
import com.tbs.covidtracker.R
import com.tbs.covidtracker.util.viewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LauncherActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        val viewModel = viewModelProvider<LauncherViewModel>(viewModelFactory)
        viewModel.liveData.observe(this, Observer {
            when (it) {
                is LauncherViewModel.LaunchState.GoToMain -> {
                    val intent = Intent().apply {
                        setClass(this@LauncherActivity, HomeActivity::class.java)
                    }
                    startActivity(intent)
                    finish()
                }
            }
        })
    }
}