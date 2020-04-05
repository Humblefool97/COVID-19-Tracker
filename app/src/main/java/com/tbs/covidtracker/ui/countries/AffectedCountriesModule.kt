package com.tbs.covidtracker.ui.countries

import androidx.lifecycle.ViewModel
import com.tbs.covidtracker.di.customscope.FragmentScoped
import com.tbs.covidtracker.di.customscope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AffectedCountriesModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun injectAffectedCountriesFragment(): AffectedCountriesFragment


    @Binds
    @IntoMap
    @ViewModelKey(AffectedCountriesViewModel::class)
    abstract fun provideAffectedCountriesViewModel(viewModel: AffectedCountriesViewModel): ViewModel

}