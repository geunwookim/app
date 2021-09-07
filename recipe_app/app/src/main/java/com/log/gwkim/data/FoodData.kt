package com.log.gwkim.data

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodData(
    val id: Long,
    val name: String,
    val ingredients: String,
    val recipe: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val imageByteArray: ByteArray?,
    @DrawableRes
    val image: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var key: Long = 0
}

@Entity(tableName = "checkInit")
data class CheckData(
    val init: Boolean,
    val id: Long
) {
    @PrimaryKey(autoGenerate = true)
    var key: Long = 0
}