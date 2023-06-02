package com.kalex.bookyouu_app.domain.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): AuthResult
    suspend fun logout()
}
