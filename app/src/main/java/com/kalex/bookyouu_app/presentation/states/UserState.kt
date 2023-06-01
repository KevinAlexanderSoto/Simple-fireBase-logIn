package com.kalex.bookyouu_app.presentation.states

import com.kalex.bookyouu_app.data.remote.dto.Userdto

data class UserState(
    val isLoading: Boolean = false,
    var user: Userdto? = null,
    val error: String = "",

)
