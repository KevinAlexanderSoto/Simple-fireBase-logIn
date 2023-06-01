package com.kalex.bookyouu_app.domain.use_case.authentication

import com.google.firebase.auth.AuthResult
import com.kalex.bookyouu_app.common.Resource
import com.kalex.bookyouu_app.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authRepository: AuthenticationRepository,
) {
    fun login(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        try {
            emit(Resource.Loading<AuthResult>())
            val user = authRepository.login(email, password)
            emit(Resource.Success<AuthResult>(user))
        } catch (e: HttpException) {
            emit(Resource.Error<AuthResult>(e.localizedMessage ?: "an unexpeted error occured"))
        } catch (e: IOException) { // no puede comunicarse sin internet por ejemplo
            emit(Resource.Error<AuthResult>("error internet connection"))
        }
    }

    suspend fun logout(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            authRepository.logout()
            emit(Resource.Success<Boolean>(true))
        } catch (e: HttpException) {
            emit(Resource.Error<Boolean>(e.localizedMessage ?: "an unexpeted error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Boolean>("error internet connection"))
        }
    }
}
