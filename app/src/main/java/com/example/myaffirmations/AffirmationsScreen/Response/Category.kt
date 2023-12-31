package com.example.myaffirmations.AffirmationsScreen.Response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "pretty_slug")
    val prettySlug: String,
    @Json(name = "slug")
    val slug: String
)