package com.log.gwkim.fragment.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.log.gwkim.R
import com.log.gwkim.data.CheckData
import com.log.gwkim.data.FoodData
import com.log.gwkim.repository.FoodRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private val foodRepositoryImpl: FoodRepositoryImpl) : ViewModel() {

    fun insertFood(foodId: Long, foodName: String?, foodIngredients: String?, foodRecipe: String?, foodImageByteArray: ByteArray?) {
        if (foodName == null || foodIngredients == null || foodRecipe == null) {
            return
        }

        val foodImage = R.drawable.korea
        val newFood = FoodData(
            foodId,
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

    fun initCheckIsFirst() {
        foodRepositoryImpl.initCheckIsFirst()
    }

    fun initCheckIsDone() : LiveData<Boolean> {
        return foodRepositoryImpl.initCheckDone
    }

    fun initCheckInsert() {
        foodRepositoryImpl.initCheckInsert()
    }

    fun getInitData() : List<CheckData> {
        return foodRepositoryImpl.initDataList
    }

    fun initDataIsDone() : LiveData<Boolean> {
        return foodRepositoryImpl.initDataDone
    }

    fun initDataInsert() {
        foodRepositoryImpl.initDataInsert()
    }
}