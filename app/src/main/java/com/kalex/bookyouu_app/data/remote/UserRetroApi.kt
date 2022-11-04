package com.kalex.bookyouu_app.data.remote

import com.kalex.bookyouu_app.data.remote.dto.*
import com.kalex.bookyouu_app.data.remote.dto.Userdto
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserRetroApi {

    @GET("admin/student")
    suspend fun getUser(
    ): Userdto


}