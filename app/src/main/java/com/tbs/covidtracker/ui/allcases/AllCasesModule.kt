package com.tbs.covidtracker.ui.allcases

import androidx.lifecycle.ViewModel
import com.tbs.covidtracker.di.customscope.ChildFragmentScoped
import com.tbs.covidtracker.di.customscope.FragmentScoped
import com.tbs.covidtracker.di.customscope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AllCasesModule {
    /**
     * Provides [dagger.android.AndroidInjector] for [DetailsFragment]
     */
    @ChildFragmentScoped
    @ContributesAndroidInjector
    abstract fun bindAllCasesFragment(): DetailsFragment
    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [AllCasesViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(AllCasesViewModel::class)
    abstract fun bindLaunchViewModel(viewModel: AllCasesViewModel): ViewModel
}