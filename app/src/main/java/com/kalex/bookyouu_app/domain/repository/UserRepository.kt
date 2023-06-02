package com.kalex.bookyouu_app.domain.repository

import com.kalex.bookyouu_app.data.remote.dto.Userdto

interface UserRepository {
    suspend fun getUser(): Userdto
}
