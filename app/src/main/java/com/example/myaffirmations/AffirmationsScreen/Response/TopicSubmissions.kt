package com.example.myaffirmations.AffirmationsScreen.Response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopicSubmissions(
    @Json(name = "architecture-interior")
    val architectureInterior: ArchitectureInterior?,
    @Json(name = "color-of-water")
    val colorOfWater: ColorOfWater?,
    @Json(name = "current-events")
    val currentEvents: CurrentEvents?,
    @Json(name = "nature")
    val nature: Nature?,
    @Json(name = "wallpapers")
    val wallpapers: Wallpapers?
)