package com.mansi.stackoverflowlisttask.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemsList(
    @Json(name = "has_more")val hasMore: Boolean,
    @Json(name = "quota_max")val quotaMax : Int,
    @Json(name = "quota_remaining")val quotaRemaining: Int,
    @Json(name = "items")val items : List<Items>
)

