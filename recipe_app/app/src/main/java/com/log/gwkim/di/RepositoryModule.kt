package com.log.gwkim.di

import com.log.gwkim.data.FoodDao
import com.log.gwkim.repository.FoodRepositoryImpl
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