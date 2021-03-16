package com.mansi.stackoverflowlisttask.di

import android.content.Context
import com.mansi.stackoverflowlisttask.data.local.AppDatabase
import com.mansi.stackoverflowlisttask.data.local.ItemsDao
import com.mansi.stackoverflowlisttask.data.remote.ItemsRemoteDataSource
import com.mansi.stackoverflowlisttask.data.remote.ItemsService
import com.mansi.stackoverflowlisttask.data.repository.ItemsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

     val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.stackexchange.com/2.2/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideItemService(retrofit: Retrofit): ItemsService = retrofit.create(ItemsService::class.java)

    @Singleton
    @Provides
    fun provideItemRemoteDataSource(itemsService: ItemsService) = ItemsRemoteDataSource(itemsService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideItemDao(db: AppDatabase) = db.itemDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: ItemsRemoteDataSource,
                          localDataSource: ItemsDao) =
        ItemsRepository(remoteDataSource, localDataSource)
}