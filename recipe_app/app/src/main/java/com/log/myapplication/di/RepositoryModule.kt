package com.log.myapplication.di

import com.log.myapplication.data.FoodDao
import com.log.myapplication.repository.FoodRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideFoodRepository(foodDao: FoodDao) : FoodRepositoryImpl {
        return FoodRepositoryImpl(foodDao)
    }
}