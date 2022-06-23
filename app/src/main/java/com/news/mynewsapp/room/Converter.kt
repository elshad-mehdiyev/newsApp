package com.news.mynewsapp.room

import androidx.room.TypeConverter
import com.news.mynewsapp.model.Source


class Converter {
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String) : Source {
        return Source(name, name)
    }
}