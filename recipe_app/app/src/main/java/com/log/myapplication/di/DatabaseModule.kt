package com.log.myapplication.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.log.myapplication.data.AppDatabase
import com.log.myapplication.data.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext,
            AppDatabase::class.java,
            "Food_Database"
        ).build()
    }

    @Provides
    fun provideFoodDao(appDatabase: AppDatabase): FoodDao {
        return appDatabase.getFoodDao()
    }
}