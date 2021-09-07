package com.log.gwkim.foodDetail

import androidx.lifecycle.ViewModel
import com.log.gwkim.data.FoodData
import com.log.gwkim.repository.FoodRepositoryImpl
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