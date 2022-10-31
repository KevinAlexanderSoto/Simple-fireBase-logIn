package com.kalex.bookyouu_app.data.remote.dto

import com.kalex.bookyouu_app.domain.model.Ofice

data class OficeDto(
    val Count: Int,
    val Items: List<ItemOficeDto>,
    val ScannedCount: Int
)

fun OficeDto.toOfice(): Ofice {
    return Ofice(
     Items= Items.map { it.toItemOfice() }
    )
}