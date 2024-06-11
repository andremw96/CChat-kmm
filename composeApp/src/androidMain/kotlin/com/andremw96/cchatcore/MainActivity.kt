package com.andremw96.cchatcore

import RegisterScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.androidx.viewmodel.ext.android.getViewModel
import uicompose.screen.register.RegisterViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            System.loadLibrary("ecdhcurve25519");
            Log.i(
                "Load ecdh",
                "Loaded ecdhcurve25519 library."
            )
        } catch (e: UnsatisfiedLinkError) {
            Log.i(
                "Load ecdh",
                "Load failed ecdhcurve25519 library."
            )
        }

        val registerViewModel: RegisterViewModel = getViewModel()
        setContent {
            RegisterScreen(
                registerViewModel
            )
        }
    }
}