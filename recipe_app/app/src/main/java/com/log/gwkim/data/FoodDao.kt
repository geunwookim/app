package com.log.gwkim.data

import androidx.room.*

@Dao
interface FoodDao {
    @Insert
    fun insert(vararg foodData: FoodData)

    @Update
    fun update(foodData: FoodData)

    @Delete
    fun delete(foodData: FoodData)

    @Query("SELECT * FROM [food]")
    fun getAll(): List<FoodData>

    @Query("DELETE FROM [food] WHERE id= :id")
    fun deleteFoodById(id: Long)

    @Query("SELECT * FROM [checkInit]")
    fun checkIsFirst(): List<CheckData>

    @Query("insert into [checkInit] values(1,1,0)")
    fun initCheckInsert()
}