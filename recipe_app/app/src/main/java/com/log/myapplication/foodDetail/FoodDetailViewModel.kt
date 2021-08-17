package com.log.myapplication.foodDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.log.myapplication.data.FoodData
import com.log.myapplication.data.FoodDataSource

class FoodDetailViewModel(private val foodDataSource: FoodDataSource) : ViewModel() {

    fun getFoodForId(id: Long): FoodData? {
        return foodDataSource.getFoodForId(id)
    }

    fun removeFood(foodData: FoodData) {
        foodDataSource.removeFood(foodData)
    }
}

class FoodDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FoodDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FoodDetailViewModel(
                foodDataSource = FoodDataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}