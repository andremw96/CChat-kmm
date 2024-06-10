package com.andremw96.cchatcore.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uicompose.screen.register.RegisterViewModel

val androidViewModelModule = module {
    viewModel {
        RegisterViewModel(get(), get())
    }
}
