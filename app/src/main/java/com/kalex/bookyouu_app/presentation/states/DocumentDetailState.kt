package com.kalex.bookyouu_app.presentation.states

import com.kalex.bookyouu_app.data.remote.dto.DocumentDetailDto
import com.kalex.bookyouu_app.data.remote.dto.DocumentDto

data class DocumentDetailState (
    val isLoading:Boolean = false,
    val document : DocumentDto? = null,
    val error :String = ""
)