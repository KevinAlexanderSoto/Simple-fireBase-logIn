package com.kalex.bookyouu_app.presentation.validations

import java.io.File

fun File.getFileSizeFloat(): Double {
    return this.length().toDouble().div(1024)
}