package com.mansi.stackoverflowlisttask.utils

import androidx.room.TypeConverter
import com.mansi.stackoverflowlisttask.data.entities.Owner
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.util.*


class ItemsTypeConverter {

    val moshi = Moshi.Builder().build()
    //for date and time convertions
    @TypeConverter
    fun calendarToDateStamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun dateStampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }


    /*  for converting List<String?>?  you can do same with other data type*/
    @TypeConverter
    fun toStringToList(listOfString: List<String>): String? {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = moshi.adapter<List<String>>(type)
        return adapter.toJson(listOfString)
    }

    @TypeConverter
    fun fromStringToList(string: String?): List<String>? {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = moshi.adapter<List<String>>(type)
        return adapter.fromJson(string)

    }

    // for converting the json object or String into Pojo or DTO class
    @TypeConverter
    fun toOwner(value: String?): Owner? {
        val jsonAdapter: JsonAdapter<Owner> = moshi.adapter(Owner::class.java)
        return jsonAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromOwner(owner: Owner?): String {
        val jsonAdapter: JsonAdapter<Owner> = moshi.adapter(Owner::class.java)
        return jsonAdapter.toJson(owner)

    }

}