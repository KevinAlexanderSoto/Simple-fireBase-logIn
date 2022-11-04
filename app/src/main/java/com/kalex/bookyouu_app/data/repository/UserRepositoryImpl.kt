package com.kalex.bookyouu_app.data.repository

import com.kalex.bookyouu_app.data.remote.UserRetroApi
import com.kalex.bookyouu_app.data.remote.dto.*
import com.kalex.bookyouu_app.domain.repository.UserRepository
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api : UserRetroApi
):UserRepository {
    override suspend fun getUser(): Userdto{
        return api.getUser()
    }

}