package com.example.myaffirmations.AffirmationsScreen.Response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "download")
    val download: String,
    @Json(name = "download_location")
    val downloadLocation: String,
    @Json(name = "html")
    val html: String,
    @Json(name = "self")
    val self: String
)