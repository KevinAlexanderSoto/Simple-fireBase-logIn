package com.kalex.bookyouu_app.data.remote.dto

import com.kalex.bookyouu_app.domain.model.User

data class Userdto(
    val msg: String = "",
    val usuario: String = "",
)

fun Userdto.toUser(): User {
    return User(
        smg = msg,
        usuario = usuario,
    )
}
