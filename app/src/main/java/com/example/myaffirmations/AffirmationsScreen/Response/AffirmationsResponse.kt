package com.example.myaffirmations.AffirmationsScreen.Response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AffirmationsResponse(
    @Json(name = "author")
    val author: String,
    @Json(name = "quote")
    val quote: String
)