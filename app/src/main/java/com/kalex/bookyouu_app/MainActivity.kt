package com.kalex.bookyouu_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.kalex.bookyouu_app.navigation.Navegacion
import com.kalex.bookyouu_app.res.theme.BYAplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalPermissionsApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BYAplicationTheme {
                Navegacion()
            }
        }
    }
}

