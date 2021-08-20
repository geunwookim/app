package com.log.myapplication.repository

import androidx.lifecycle.LiveData
import com.log.myapplication.data.FoodData

interface FoodRepository {
    fun addFood(foodData: FoodData)
    fun removeFood(foodData: FoodData)
    fun getFoodForId(id: Long): FoodData?
    fun getFoodList(): LiveData<List<FoodData>>
}