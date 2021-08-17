package com.log.myapplication.fragment.first

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.log.myapplication.R
import com.log.myapplication.data.FoodData
import com.log.myapplication.data.FoodDataSource
import java.lang.IllegalArgumentException
import kotlin.random.Random

class FoodViewModel(private val foodDataSource: FoodDataSource) : ViewModel() {

    val foodLiveData = foodDataSource.getFoodList()

    fun insertFood(foodName: String?, foodIngredients: String?, foodRecipe: String?) {
        if (foodName == null || foodIngredients == null || foodRecipe == null) {
            return
        }

        val foodImage = R.drawable.korea
        val newFood = FoodData(
            Random.nextLong(),
            foodName,
            foodIngredients,
            foodRecipe,
            foodImage
        )

        foodDataSource.addFood(newFood)
    }
}

class FoodViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FoodViewModel(
                foodDataSource = FoodDataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}