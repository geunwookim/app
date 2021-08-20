package com.log.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FoodData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFoodDao(): FoodDao
}