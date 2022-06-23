package com.news.mynewsapp.hilt

import android.content.Context
import androidx.room.Room
import com.news.mynewsapp.room.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    @Named("TestDataBase")
    fun injectTestDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
        context,DataBase::class.java
    ).allowMainThreadQueries().build()
}