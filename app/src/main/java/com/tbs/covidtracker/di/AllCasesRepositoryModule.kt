package com.tbs.covidtracker.di

import com.tbs.covidtracker.AllCasesRepository
import com.tbs.covidtracker.network.COVIDApiService
import dagger.Module
import dagger.Provides

@Module
class AllCasesRepositoryModule {

    @Provides
    fun provideAllCasesRepository(allCasesApiService: COVIDApiService):AllCasesRepository {
        return  AllCasesRepository(allCasesApiService)
    }
}