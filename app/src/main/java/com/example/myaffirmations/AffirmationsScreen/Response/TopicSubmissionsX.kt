package com.example.myaffirmations.AffirmationsScreen.Response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopicSubmissionsX(
    @Json(name = "business-work")
    val businessWork: BusinessWork?,
    @Json(name = "interiors")
    val interiors: Interiors?,
)