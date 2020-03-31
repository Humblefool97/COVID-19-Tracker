package com.tbs.covidtracker.ui.launcher

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LauncherActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashActivityInjector():LauncherActivity
}