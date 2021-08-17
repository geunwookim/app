package com.log.myapplication.data

import androidx.annotation.DrawableRes

data class FoodData (
    val id: Long,
    val name: String,
    val ingredients: String,
    val recipe: String,
    @DrawableRes
    val image: Int?
)