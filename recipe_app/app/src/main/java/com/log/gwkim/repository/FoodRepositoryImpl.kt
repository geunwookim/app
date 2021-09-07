package com.log.gwkim.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.log.gwkim.data.CheckData
import com.log.gwkim.data.FoodDao
import com.log.gwkim.data.FoodData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(private val foodDao: FoodDao) : FoodRepository {
    private lateinit var mergeFoodList : MutableList<FoodData>
    private val _foodLiveData :MutableLiveData<List<FoodData>> = MutableLiveData()
    private val foodLiveData : LiveData<List<FoodData>>
            get() = _foodLiveData
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    var initDataList : List<CheckData> = listOf(CheckData(false, 0))
    var initCheckDone : MutableLiveData<Boolean> = MutableLiveData(false)
    var initDataDone : MutableLiveData<Boolean> = MutableLiveData(false)

    override fun addFood(foodData: FoodData) {
        val currentList = _foodLiveData.value
        if (currentList == null) {
            _foodLiveData.value = listOf(foodData)
        } else {
            val updatedList = currentList.toMutableList()
            val lastIndex = updatedList.lastIndex
            updatedList.add(lastIndex+1, foodData)
            _foodLiveData.value = updatedList
        }
        executorService.execute {
            foodDao.insert(foodData)
        }
        executorService.awaitTermination(10,TimeUnit.MILLISECONDS)
        initDataDone.postValue(true)
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
            _foodLiveData.postValue(mergeFoodList)
        }

        return foodLiveData
    }

    override fun initCheckIsFirst() {
        executorService.execute {
            initDataList = foodDao.checkIsFirst()
            initCheckDone.postValue(true)
        }
        executorService.awaitTermination(10,TimeUnit.MILLISECONDS)
    }

    override fun initCheckInsert() {
        executorService.execute {
            foodDao.initCheckInsert()
        }
    }

    override fun initDataInsert() {
        executorService.execute {
            initDataDone.postValue(true)
        }
    }
}