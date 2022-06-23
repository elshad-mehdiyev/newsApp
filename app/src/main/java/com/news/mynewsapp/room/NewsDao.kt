package com.news.mynewsapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.news.mynewsapp.model.Articles

@Dao
interface NewsDao {
    @Query("SELECT * FROM article")
    fun showSavedNews(): LiveData<List<Articles>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(articles: Articles)
    @Delete
    suspend fun deleteNews(articles: Articles)
    @Query("DELETE FROM article")
    suspend fun deleteAll()
    @Query("SELECT * FROM article")
    fun getArticleList(): List<Articles>

}