package com.andremw96.cchatcore

import RegisterScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.androidx.viewmodel.ext.android.getViewModel
import uicompose.screen.register.RegisterViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val registerViewModel: RegisterViewModel = getViewModel()
        setContent {
            RegisterScreen(
                registerViewModel
            )
        }
    }
}