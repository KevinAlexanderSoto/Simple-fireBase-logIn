package com.kalex.bookyouu_app.domain.use_case.authentication

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.kalex.bookyouu_app.common.Resource
import com.kalex.bookyouu_app.data.remote.dto.Userdto
import com.kalex.bookyouu_app.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
  private  val authRepository : AuthenticationRepository
) {
  operator fun invoke(email: String, password: String) : Flow<Resource<AuthResult>> = flow{
    try {
      emit(Resource.Loading<AuthResult>())
      val user = authRepository.login(email,password)
      emit(Resource.Success<AuthResult>(user))
    }catch (e: HttpException){
      emit(Resource.Error<AuthResult>(e.localizedMessage ?: "an unexpeted error occured"))
    }catch (e: IOException){ // no puede comunicarse sin internet por ejemplo
      emit(Resource.Error<AuthResult>("error internet connection"))
    }
  }
}