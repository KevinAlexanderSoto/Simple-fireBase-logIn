package com.kalex.bookyouu_app.presentation.states

import com.kalex.bookyouu_app.data.remote.dto.DocumentDetailDto
import com.kalex.bookyouu_app.data.remote.dto.DocumentDto
import com.kalex.bookyouu_app.domain.model.Document


data class DocumentState(
    val isLoading:Boolean = false,
    val document : DocumentDetailDto? = null,
    val error :String = ""
)
