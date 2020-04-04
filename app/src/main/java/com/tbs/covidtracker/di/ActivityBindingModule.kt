package com.tbs.covidtracker.di

import com.tbs.covidtracker.di.customscope.ActivityScoped
import com.tbs.covidtracker.ui.launch.LauncherActivity
import com.tbs.covidtracker.ui.launch.LauncherModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LauncherModule::class])
    internal abstract fun launcherActivity(): LauncherActivity
}