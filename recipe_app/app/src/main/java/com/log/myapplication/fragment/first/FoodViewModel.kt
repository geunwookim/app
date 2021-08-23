package com.log.myapplication.fragment.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.log.myapplication.R
import com.log.myapplication.data.FoodData
import com.log.myapplication.repository.FoodRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class FoodViewModel @Inject constructor(private val foodRepositoryImpl: FoodRepositoryImpl) : ViewModel() {

    fun insertFood(foodName: String?, foodIngredients: String?, foodRecipe: String?, foodImageByteArray: ByteArray?) {
        if (foodName == null || foodIngredients == null || foodRecipe == null) {
            return
        }

        val foodImage = R.drawable.korea
        val newFood = FoodData(
            Random.nextLong(),
            foodName,
            foodIngredients,
            foodRecipe,
            foodImageByteArray,
            foodImage
        )

        foodRepositoryImpl.addFood(newFood)
    }

    fun getFoodList() : LiveData<List<FoodData>> {
        return foodRepositoryImpl.getFoodList()
    }
}