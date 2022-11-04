package com.kalex.bookyouu_app.domain.repository

import com.kalex.bookyouu_app.data.remote.dto.*
import okhttp3.RequestBody
import retrofit2.Response

interface UserRepository {
    suspend fun getUser():Userdto

}