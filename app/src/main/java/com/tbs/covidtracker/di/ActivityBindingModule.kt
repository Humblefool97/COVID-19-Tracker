package com.tbs.covidtracker.di

import com.tbs.covidtracker.HomeActivity
import com.tbs.covidtracker.di.customscope.ActivityScoped
import com.tbs.covidtracker.ui.allcases.AllCasesModule
import com.tbs.covidtracker.ui.countries.AffectedCountriesModule
import com.tbs.covidtracker.ui.launch.LauncherActivity
import com.tbs.covidtracker.ui.launch.LauncherModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LauncherModule::class])
    internal abstract fun launcherActivity(): LauncherActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            AllCasesModule::class,
            AffectedCountriesModule::class
        ]
    )
    internal abstract fun launchHomeActivity(): HomeActivity

}