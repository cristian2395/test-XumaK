package com.example.xumak.repository.bd.typeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList

object ConvertersArrayInt {
    @TypeConverter
    fun toArrayList(value: String?): ArrayList<Int> {
        val listType: Type = object : TypeToken<ArrayList<Int?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Int?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}