package com.shreyasmp.nbateams.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shreyasmp.nbateams.model.Player

object PlayersListTypeConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): List<Player> {
        if (data.isNullOrBlank()) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Player>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun listToString(someObject: List<Player>): String {
        return gson.toJson(someObject)
    }
}