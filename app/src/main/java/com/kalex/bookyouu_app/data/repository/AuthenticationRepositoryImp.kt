package com.kalex.bookyouu_app.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kalex.bookyouu_app.common.Resource
import com.kalex.bookyouu_app.common.await
import com.kalex.bookyouu_app.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
private val firebaseClient: FirebaseAuth
) : AuthenticationRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseClient.currentUser

    override suspend fun login(email: String, password: String): AuthResult {
       return firebaseClient.signInWithEmailAndPassword(email,password).await()
    }

    override suspend fun logout() {
        firebaseClient.signOut()
    }
}