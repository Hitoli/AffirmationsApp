package com.example.myaffirmations.AffirmationsScreen.Response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tag(
    @Json(name = "source")
    val source: Source?,
    @Json(name = "title")
    val title: String,
    @Json(name = "type")
    val type: String
)