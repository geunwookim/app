package com.log.myapplication.data

import android.content.res.Resources
import com.log.myapplication.R

fun foodList(resources: Resources): List<FoodData> {
    return listOf(
        FoodData(
            id = 1,
            name = "비빔밥",
            ingredients = "쌀, 채소",
            recipe = "1. 재료를 넣는다\r\n" +
                    "2. 비빈다",
            image = R.drawable.korea
        ),
        FoodData(
            id = 2,
            name = "소불고기",
            ingredients = "소고기, 채소",
            recipe = "1. 이건 잘 모름\r\n"+
                    "2. 알아서 맛있게 해 먹는다",
            image = R.drawable.korea
        ),
        FoodData(
            id = 3,
            name = "케밥",
            ingredients = "케, 밥",
            recipe = "1. 케밥 맛있지",
            image = R.drawable.korea
        ),
        FoodData(
            id = 4,
            name = "파스타",
            ingredients ="파스타면, 채소",
            recipe = "1. 파스타 더 맛있지",
            image = R.drawable.korea
        ),
        FoodData(
            id = 5,
            name = "카레",
            ingredients = "밥, 카레가루",
            recipe = "1. 난이랑 라씨 맛있지",
            image = R.drawable.korea
        )
    )
}
