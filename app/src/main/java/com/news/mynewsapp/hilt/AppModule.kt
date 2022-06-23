package com.news.mynewsapp.hilt

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.news.mynewsapp.R
import com.news.mynewsapp.constants.Constant.Companion.BASE_URL
import com.news.mynewsapp.constants.Constant.Companion.DATABASE_NAME
import com.news.mynewsapp.repository.NewsRepository
import com.news.mynewsapp.repository.NewsRepositoryInterface
import com.news.mynewsapp.room.DataBase
import com.news.mynewsapp.room.NewsDao
import com.news.mynewsapp.service.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        DataBase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideNewsDao(db: DataBase) = db.getDao()

    @Singleton
    @Provides
    fun provideNewsRetrofitService(): NewsApi {
         return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build().create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun injectInterfaceRepository(dao: NewsDao, api: NewsApi) = NewsRepository(dao, api) as NewsRepositoryInterface


    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide
        .with(context).setDefaultRequestOptions(
            RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )

    /************************
     * for CoroutineScope  Injection
    * @Singleton
    * @Provides
    * fun injectCoroutineScope() : CoroutineScope {
    *     return  CoroutineScope(SupervisorJob() + Dispatchers.Default)
    * }
    *************************/

}
