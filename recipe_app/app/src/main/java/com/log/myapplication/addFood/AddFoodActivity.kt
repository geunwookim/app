package com.log.myapplication.addFood

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.textfield.TextInputEditText
import com.log.myapplication.R
import com.log.myapplication.databinding.ActivityAddFoodBinding

const val FOOD_NAME = "name"
const val FOOD_INGREDIENTS = "ingredients"
const val FOOD_RECIPE = "recipe"

class AddFoodActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityAddFoodBinding
    private lateinit var addFoodName: TextInputEditText
    private lateinit var addFoodIngredients: TextInputEditText
    private lateinit var addFoodRecipe: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityAddFoodBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.doneButton.setOnClickListener {
            addFood()
        }

        addFoodName = mBinding.addFoodName
        addFoodIngredients = mBinding.addFoodIngredients
        addFoodRecipe = mBinding.addRecipe
    }

    private fun addFood() {
        val resultIntent = Intent()

        if(addFoodName.text.isNullOrEmpty()
            || addFoodIngredients.text.isNullOrEmpty()
            || addFoodRecipe.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val name = addFoodName.text.toString()
            val ingredients = addFoodIngredients.text.toString()
            val recipe = addFoodRecipe.text.toString()
            resultIntent.putExtra(FOOD_NAME, name)
            resultIntent.putExtra(FOOD_INGREDIENTS, ingredients)
            resultIntent.putExtra(FOOD_RECIPE, recipe)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
}