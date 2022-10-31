package com.kalex.bookyouu_app.presentation.states


import com.kalex.bookyouu_app.data.remote.dto.postDocumentDto

data class PostDocumentState (
        val isLoading:Boolean = false,
        val respuesta : postDocumentDto? = null,
        val error :String = ""
)