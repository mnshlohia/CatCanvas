package com.example.catcanvas.data.remote.dto

import com.squareup.moshi.Json

data class CatListingItemDTO(
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "id") val id: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "width") val width: Int
)