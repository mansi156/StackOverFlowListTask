package com.mansi.stackoverflowlisttask.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mansi.stackoverflowlisttask.utils.ItemsTypeConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "items")
@TypeConverters(ItemsTypeConverter::class)
@JsonClass(generateAdapter = true)
data class Items(
    @Json(name = "answer_count")val answerCount: Int,
    @Json(name = "content_license")val contentLicense: String? = null,
    @Json(name = "creation_date")val creationDate: Long,
    @Json(name = "is_answered")val isAnswered: Boolean,
    @Json(name = "last_activity_date")val lastActivityDate: Long,
    val link: String,
    val owner: Owner,
    @PrimaryKey
    @Json(name = "question_id")val questionId: Int,
    val score: Int,
    val tags : List<String>,
    val title: String,
    @Json(name = "view_count")val viewCount: Int
)


