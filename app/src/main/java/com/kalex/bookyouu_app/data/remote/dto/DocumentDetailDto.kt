package com.kalex.bookyouu_app.data.remote.dto

data class DocumentDetailDto(
    val Count: Int,
    val Items: List<ItemDocDto>,
    val ScannedCount: Int
)