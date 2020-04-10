package com.tbs.covidtracker.ui.country

import com.tbs.covidtracker.di.customscope.FragmentScoped
import com.tbs.covidtracker.ui.allcases.AllCasesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CountryDetailModule {

    /**
     * Provides [dagger.android.AndroidInjector] for [CountryDetailFragment]
     */
    @FragmentScoped
    @ContributesAndroidInjector(modules = [AllCasesModule::class])
    abstract fun bindCountryDetailFragment(): CountryDetailFragment
}