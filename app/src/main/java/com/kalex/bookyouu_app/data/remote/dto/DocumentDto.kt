package com.kalex.bookyouu_app.data.remote.dto

import com.kalex.bookyouu_app.domain.model.Document

data class DocumentDto(
    val Count: Int,
    val Items: List<ItemDto>,
    val ScannedCount: Int
)

fun DocumentDto.toDocument(): Document {
    return Document(
        Items =  Items.map { it.toItem() },

    )
}