package com.kalex.bookyouu_app.data.remote

import com.kalex.bookyouu_app.data.remote.dto.*
import com.kalex.bookyouu_app.data.remote.dto.Userdto
import retrofit2.http.GET

interface UserRetroApi {

    @GET("admin/student")
    suspend fun getUser(): Userdto
}
