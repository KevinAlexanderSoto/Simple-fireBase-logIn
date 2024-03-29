package com.kalex.bookyouu_app.res.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun ButtonText(msg: String, tamañoLetra: Int = 20) {
    Text(
        text = msg,
        fontSize = tamañoLetra.sp,

    )
}
