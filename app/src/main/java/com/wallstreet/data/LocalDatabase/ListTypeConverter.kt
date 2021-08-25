package com.wallstreet.data.LocalDatabase

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListTypeConverter {

    @TypeConverter
    fun fromListToString(list: List<String>?): String {
        if (list == null) return ""
        val gson = Gson()
        val type = object : TypeToken<List<String>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun fromStringToList(value: String?): List<String>? {
        if (value.isNullOrEmpty()) return null
        val gson = Gson()
        val type = object : TypeToken<List<String>?>() {}.type
        return gson.fromJson(value, type)
    }
}