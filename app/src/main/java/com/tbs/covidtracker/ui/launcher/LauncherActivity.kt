package com.tbs.covidtracker.ui.launcher

import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

class LauncherActivity: DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}