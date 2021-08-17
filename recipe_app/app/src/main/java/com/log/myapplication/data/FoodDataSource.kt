package com.log.myapplication.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.sql.DataSource

class FoodDataSource(resources: Resources) {
    private val initialFoodList = foodList(resources)
    private val foodLiveData = MutableLiveData(initialFoodList)

    fun addFood(foodData: FoodData) {
        val currentList = foodLiveData.value
        if (currentList == null) {
            foodLiveData.postValue(listOf(foodData))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, foodData)
            foodLiveData.postValue(updatedList)
        }
    }

    fun removeFood(foodData: FoodData) {
        val currentList = foodLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(foodData)
            foodLiveData.postValue(updatedList)
        }
    }

    fun getFoodForId(id: Long): FoodData? {
        foodLiveData.value?.let { foodData ->
            return foodData.firstOrNull{ it.id == id }
        }
        return null
    }

    fun getFoodList(): LiveData<List<FoodData>> {
        return foodLiveData
    }

    companion object {
        private var INSTANCE: FoodDataSource? = null

        fun getDataSource(resources: Resources): FoodDataSource {
            return synchronized(FoodDataSource::class) {
                val newInstance = INSTANCE?: FoodDataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}