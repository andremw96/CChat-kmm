package com.andremw96.cchatcore

import android.app.Application
import com.andremw96.cchatcore.di.androidViewModelModule
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CChatApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)

        startKoin {
            androidContext(this@CChatApp)
            androidLogger()
            modules(
                androidViewModelModule + appModule()
            )
        }
    }
}