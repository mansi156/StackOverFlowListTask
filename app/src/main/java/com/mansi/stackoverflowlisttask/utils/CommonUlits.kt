package com.mansi.stackoverflowlisttask.utils

import android.text.format.DateUtils
import java.util.*

object CommonUtils {
    fun getRelationTime(time: Long): String {
        val now: Long = Date().time
        val delta = now - time
        val resolution: Long = when {
            delta <= DateUtils.MINUTE_IN_MILLIS -> {
                DateUtils.SECOND_IN_MILLIS
            }
            delta <= DateUtils.HOUR_IN_MILLIS -> {
                DateUtils.MINUTE_IN_MILLIS
            }
            delta <= DateUtils.DAY_IN_MILLIS -> {
                DateUtils.HOUR_IN_MILLIS
            }
            delta <= DateUtils.WEEK_IN_MILLIS -> {
                DateUtils.DAY_IN_MILLIS
            }
            else -> return when {
                delta <= AVERAGE_MONTH_IN_MILLIS -> {
                    (delta / DateUtils.WEEK_IN_MILLIS).toInt().toString() + " weeks(s) ago"
                }
                delta <= DateUtils.YEAR_IN_MILLIS -> {
                    (delta / AVERAGE_MONTH_IN_MILLIS).toInt().toString() + " month(s) ago"
                }
                else -> {
                    (delta / DateUtils.YEAR_IN_MILLIS).toInt().toString() + " year(s) ago"
                }
            }
        }
        return DateUtils.getRelativeTimeSpanString(time, now, resolution).toString()
    }


    private const val AVERAGE_MONTH_IN_MILLIS = DateUtils.DAY_IN_MILLIS * 30

}