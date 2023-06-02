package com.kalex.bookyouu_app.domain.use_case.get_users

import com.kalex.bookyouu_app.common.Resource
import com.kalex.bookyouu_app.data.remote.dto.Userdto
import com.kalex.bookyouu_app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    operator fun invoke(): Flow<Resource<Userdto>> = flow {
        try {
            emit(Resource.Loading<Userdto>())
            val user = repository.getUser()
            emit(Resource.Success<Userdto>(user))
        } catch (e: HttpException) {
            emit(Resource.Error<Userdto>(e.localizedMessage ?: "an unexpeted error occured"))
        } catch (e: IOException) { // no puede comunicarse sin internet por ejemplo
            emit(Resource.Error<Userdto>("error internet connection"))
        }
    }
}
