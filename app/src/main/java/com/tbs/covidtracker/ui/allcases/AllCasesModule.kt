package com.tbs.covidtracker.ui.allcases

import androidx.lifecycle.ViewModel
import com.tbs.covidtracker.AllCasesRepository
import com.tbs.covidtracker.di.AllCasesRepositoryModule
import com.tbs.covidtracker.di.customscope.FragmentScoped
import com.tbs.covidtracker.di.customscope.ViewModelKey
import com.tbs.covidtracker.network.CovidApiService
import com.tbs.covidtracker.ui.launch.LauncherViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class AllCasesModule {
    /**
     * Provides [dagger.android.AndroidInjector] for [AllCasesFragment]
     */
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindAllCasesFragment(): AllCasesFragment
    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [AllCasesViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(AllCasesViewModel::class)
    abstract fun bindLaunchViewModel(viewModel: AllCasesViewModel): ViewModel
}