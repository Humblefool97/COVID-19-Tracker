package com.tbs.covidtracker.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified  VM:ViewModel> FragmentActivity.viewModelProvider(provider: ViewModelProvider.Factory) = ViewModelProviders.of(this,provider).get(VM::class.java)

inline fun <reified  VM:ViewModel> Fragment.viewModelProvider(provider: ViewModelProvider.Factory) = ViewModelProviders.of(this,provider).get(VM::class.java)