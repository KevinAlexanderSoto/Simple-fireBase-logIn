package com.kalex.bookyouu_app.domain.use_case.get_documents

import com.kalex.bookyouu_app.common.Resource
import com.kalex.bookyouu_app.data.remote.dto.DocumentDetailDto
import com.kalex.bookyouu_app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDocumentsUseCase @Inject constructor(
    private val repository: UserRepository // injectamos la interface
){
    operator fun invoke(correo :String) : Flow<Resource<DocumentDetailDto>> = flow{
        try {
            emit(Resource.Loading<DocumentDetailDto>())

                var document =  repository.getDocuments(correo)
                emit(Resource.Success<DocumentDetailDto>(document))

        }catch (e: HttpException){
            emit(Resource.Error<DocumentDetailDto>(e.localizedMessage ?: "an unexpeted error occured"))
        }catch (e: IOException){ // no puede comunicarse sin internet por ejemplo
            emit(Resource.Error<DocumentDetailDto>("error internet connection"))
        }
    }
}