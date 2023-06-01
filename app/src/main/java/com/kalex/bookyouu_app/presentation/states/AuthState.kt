package com.kalex.bookyouu_app.presentation.states

import com.google.firebase.auth.AuthResult

data class AuthState(
    val isLoading: Boolean = false,
    val isLogout: Boolean = false,
    val isLogin: AuthResult? = null,
    val isError: String = ""
)
