package com.mansi.stackoverflowlisttask.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Owner (
    @Json(name = "display_name") val displayName: String?,
    val link: String?,
    @Json(name = "profile_image")val profileImage: String?,
    val reputation: Int?,
    @Json(name = "user_id")val userId: Int?,
    @Json(name = "user_type")val userType: String?
)
