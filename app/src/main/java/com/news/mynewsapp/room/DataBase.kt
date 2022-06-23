package com.news.mynewsapp.room

import androidx.room.*
import com.news.mynewsapp.model.Articles

@Database(entities = [Articles::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class DataBase: RoomDatabase() {
    abstract fun getDao(): NewsDao
}