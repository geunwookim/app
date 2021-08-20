package com.log.myapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.log.myapplication.data.FoodDao
import com.log.myapplication.data.FoodData
import com.log.myapplication.data.foodList
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(private val foodDao: FoodDao) : FoodRepository {
    private val initialFoodList = foodList()
    private lateinit var mergeFoodList : MutableList<FoodData>
    private val _foodLiveData :MutableLiveData<List<FoodData>> = MutableLiveData()
    private val foodLiveData : LiveData<List<FoodData>>
            get() = _foodLiveData
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)

    override fun addFood(foodData: FoodData) {
        val currentList = _foodLiveData.value
        if (currentList == null) {
            _foodLiveData.postValue(listOf(foodData))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, foodData)
            _foodLiveData.postValue(updatedList)
        }
        executorService.execute {
            foodDao.insert(foodData)
        }
    }

    override fun removeFood(foodData: FoodData) {
        val currentList = _foodLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(foodData)
            _foodLiveData.postValue(updatedList)
        }
        executorService.execute {
            foodDao.deleteFoodById(foodData.id)
        }
    }

    override fun getFoodForId(id: Long): FoodData? {
        _foodLiveData.value?.let { foodData ->
            return foodData.firstOrNull{ it.id == id }
        }
        return null
    }

    override fun getFoodList(): LiveData<List<FoodData>> {
        executorService.execute {
            mergeFoodList = foodDao.getAll() as MutableList<FoodData>
            mergeFoodList.addAll(0, initialFoodList)
            _foodLiveData.postValue(mergeFoodList)
        }
        return foodLiveData
    }
}