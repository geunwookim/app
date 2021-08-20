package com.log.myapplication.data

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodData (
    val id: Long,
    val name: String,
    val ingredients: String,
    val recipe: String,
    val imageUri: String?,
    @DrawableRes
    val image: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var key: Long = 0
}