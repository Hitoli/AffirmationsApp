package com.example.myaffirmations.AffirmationsScreen.Response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "alt_description")
    val altDescription: String,
    @Json(name = "blur_hash")
    val blurHash: String,
    @Json(name = "breadcrumbs")
    val breadcrumbs: List<Any>,
    @Json(name = "color")
    val color: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "current_user_collections")
    val currentUserCollections: List<Any>,
    @Json(name = "description")
    val description: String?,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "liked_by_user")
    val likedByUser: Boolean,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "links")
    val links: Links,
    @Json(name = "promoted_at")
    val promotedAt: String?,
    @Json(name = "slug")
    val slug: String,
    @Json(name = "sponsorship")
    val sponsorship: Any?,
    @Json(name = "tags")
    val tags: List<Tag>,
    @Json(name = "topic_submissions")
    val topicSubmissions: TopicSubmissionsX?,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "urls")
    val urls: UrlsX,
    @Json(name = "user")
    val user: UserX,
    @Json(name = "width")
    val width: Int
)