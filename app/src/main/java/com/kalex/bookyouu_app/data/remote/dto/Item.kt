package com.kalex.bookyouu_app.data.remote.dto

import com.kalex.bookyouu_app.domain.model.Item

data class ItemDto(
    val Adjunto: String,
    val Apellido: String,
    val Ciudad: String,
    val Correo: String,
    val Fecha: String,
    val IdRegistro: String,
    val Identificacion: String,
    val Nombre: String,
    val TipoAdjunto: String,
    val TipoId: String
)

fun ItemDto.toItem(): Item {
    return Item(
        Adjunto = Adjunto,
        Apellido = Apellido,
        Fecha = Fecha,
        IdRegistro = IdRegistro,
        Nombre = Nombre,
        TipoAdjunto = TipoAdjunto
    )
}