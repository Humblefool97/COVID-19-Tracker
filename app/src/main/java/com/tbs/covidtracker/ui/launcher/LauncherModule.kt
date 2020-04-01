package com.tbs.covidtracker.ui.launcher

import androidx.lifecycle.ViewModel
import com.tbs.covidtracker.di.customscope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LauncherModule {

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [LauncherViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel::class)
    abstract fun bindLaunchViewModel(viewModel: LauncherViewModel): ViewModel
}