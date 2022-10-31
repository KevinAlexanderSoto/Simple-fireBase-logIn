package com.kalex.bookyouu_app.domain.use_case.get_ofices

import com.kalex.bookyouu_app.common.Resource
import com.kalex.bookyouu_app.data.remote.dto.toOfice
import com.kalex.bookyouu_app.domain.model.Ofice
import com.kalex.bookyouu_app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetOficesUseCase  @Inject constructor(
        private val repository: UserRepository // injectamos la interface
    ){
        operator fun invoke() : Flow<Resource<Ofice>> = flow{
            try {
                emit(Resource.Loading<Ofice>())
                var ofices =  repository.getAllOfices().toOfice()

                emit(Resource.Success<Ofice>(ofices))
            }catch (e: HttpException){
                emit(Resource.Error<Ofice>(e.localizedMessage ?: "an unexpeted error occured"))
            }catch (e: IOException){ // no puede comunicarse sin internet por ejemplo
                emit(Resource.Error<Ofice>("error internet connection"))
            }
        }
    }
