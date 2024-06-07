package com.andremw96.cchatcore

import android.app.Application
import di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CChatApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CChatApp)
            androidLogger()
            modules(
                appModule()
            )
        }
    }
}