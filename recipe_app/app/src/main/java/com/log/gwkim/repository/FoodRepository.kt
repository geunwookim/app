package com.log.gwkim.repository

import androidx.lifecycle.LiveData
import com.log.gwkim.data.FoodData

interface FoodRepository {
    fun addFood(foodData: FoodData)
    fun removeFood(foodData: FoodData)
    fun getFoodForId(id: Long): FoodData?
    fun getFoodList(): LiveData<List<FoodData>>
    fun initCheckIsFirst()
    fun initCheckInsert()
    fun initDataInsert()
}