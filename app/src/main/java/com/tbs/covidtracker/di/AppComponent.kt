package com.tbs.covidtracker.di

import com.tbs.covidtracker.MainApplication
import com.tbs.covidtracker.network.CovidApiService
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}