package com.log.myapplication.foodDetail

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.log.myapplication.R
import com.log.myapplication.databinding.ActivityFoodDetailBinding
import com.log.myapplication.fragment.first.FOOD_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityFoodDetailBinding
    private val foodDetailViewModel : FoodDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        var currentFoodId: Long? = null

        val foodName: TextView = findViewById(R.id.food_detail_name)
        val foodImage: ImageView = findViewById(R.id.food_detail_image)
        val foodIngredients: TextView = findViewById(R.id.food_detail_ingredients)
        val foodRecipe: TextView = findViewById(R.id.food_detail_recipe)
        val removeFoodButton: Button = findViewById(R.id.remove_button)

        val bundle: Bundle? = intent.extras
        if(bundle != null) {
            currentFoodId = bundle.getLong(FOOD_ID)
        }

        currentFoodId?.let {
            val currentFood = foodDetailViewModel.getFoodForId(it)
            foodName.text = currentFood?.name
            if(currentFood?.imageByteArray != null) {
                val bitmap = BitmapFactory.decodeByteArray(currentFood.imageByteArray, 0, currentFood.imageByteArray.size)
                foodImage.setImageBitmap(bitmap)
            } else {
                if(currentFood?.image != null) {
                    foodImage.setImageResource(currentFood.image)
                } else {
                    foodImage.setImageResource(R.drawable.korea)
                }
            }
            foodIngredients.text = currentFood?.ingredients
            foodRecipe.text = currentFood?.recipe

            removeFoodButton.setOnClickListener {
                if(currentFood != null) {
                    foodDetailViewModel.removeFood(currentFood)
                }
                finish()
            }
        }
    }
}