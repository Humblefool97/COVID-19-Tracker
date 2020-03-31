package com.tbs.covidtracker.di

import android.app.Application
import android.content.Context
import android.net.wifi.WifiManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
/**
 * Defines all the classes that need to be provided in the scope of the app.
 *
 * Define here all objects that are shared throughout the app, like SharedPreferences, navigators or
 * others. If some of those objects are singletons, they should be annotated with `@Singleton`.
 */
@Module
class AppModule {

    @Provides
    fun provideAppContext(application: Application): Context {
        return application.applicationContext
    }
    @Provides
    fun providesWifiManager(context: Context): WifiManager =
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
}