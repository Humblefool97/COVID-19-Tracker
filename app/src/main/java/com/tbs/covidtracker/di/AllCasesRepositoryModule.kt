package com.tbs.covidtracker.di

import com.tbs.covidtracker.AllCasesRepository
import com.tbs.covidtracker.network.CovidApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AllCasesRepositoryModule {

    @Provides
    fun provideAllCasesRepository(allCasesApiService: CovidApiService):AllCasesRepository {
        return  AllCasesRepository(allCasesApiService)
    }
}