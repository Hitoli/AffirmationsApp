package com.example.myaffirmations.AffirmationsScreen.Response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentEvents(
    @Json(name = "approved_on")
    val approvedOn: String,
    @Json(name = "status")
    val status: String
)