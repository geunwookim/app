package com.log.myapplication.data

import androidx.room.*

@Dao
interface FoodDao {
    @Insert
    fun insert(vararg foodData: FoodData)

    @Update
    fun update(foodData: FoodData)

    @Delete
    fun delete(foodData: FoodData)

    @Query("SELECT * FROM food ORDER BY id DESC")
    fun getAll(): List<FoodData>

    @Query("DELETE FROM food WHERE id= :id")
    fun deleteFoodById(id: Long)
}