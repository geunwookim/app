package com.log.myapplication.foodDetail

import androidx.lifecycle.ViewModel
import com.log.myapplication.data.FoodData
import com.log.myapplication.repository.FoodRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(private val foodRepositoryImpl: FoodRepositoryImpl) : ViewModel()
{
    fun getFoodForId(id: Long): FoodData? {
        return foodRepositoryImpl.getFoodForId(id)
    }

    fun removeFood(foodData: FoodData) {
        foodRepositoryImpl.removeFood(foodData)
    }
}