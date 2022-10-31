package com.kalex.bookyouu_app.presentation.states

import com.kalex.bookyouu_app.data.remote.dto.OficeDto
import com.kalex.bookyouu_app.domain.model.Ofice

data class OficeState(
    val isLoading:Boolean = false,
    val ofices: Ofice? = null,
    val error:String = ""
)