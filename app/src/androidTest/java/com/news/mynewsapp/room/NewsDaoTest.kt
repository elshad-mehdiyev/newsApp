package com.news.mynewsapp.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.news.mynewsapp.model.Articles
import com.news.mynewsapp.model.Source
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
class NewsDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("TestDataBase")
    lateinit var database: DataBase

    private lateinit var dao: NewsDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.getDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveNews() = runBlocking {
            val exampleArticles = Articles(1,"cnn","Chelsea","win","www.google.com",
            "www.hello","2022","content", Source("cnn", "cnn")
            )
            dao.saveNews(exampleArticles)

            val list = dao.showSavedNews()

            assertThat(list).contains(exampleArticles)
        }
    @Test
    fun deleteNews() = runBlocking {
        val exampleArticles = Articles(1,"cnn","Chelsea","win","www.google.com",
            "www.hello","2022","content", Source("cnn", "cnn")
        )
        dao.saveNews(exampleArticles)
        dao.deleteNews(exampleArticles)

        val list = dao.showSavedNews()
        assertThat(list).doesNotContain(exampleArticles)
    }
}