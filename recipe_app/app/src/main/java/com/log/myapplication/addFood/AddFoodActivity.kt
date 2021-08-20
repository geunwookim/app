package com.log.myapplication.addFood

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.log.myapplication.databinding.ActivityAddFoodBinding

const val FOOD_NAME = "name"
const val FOOD_INGREDIENTS = "ingredients"
const val FOOD_RECIPE = "recipe"
const val FOOD_IMAGE = "image"

private const val READ_EXTERNAL_STORAGE_REQUEST = 0x1045

class AddFoodActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityAddFoodBinding
    private lateinit var addFoodName: TextInputEditText
    private lateinit var addFoodIngredients: TextInputEditText
    private lateinit var addFoodRecipe: TextInputEditText
    private lateinit var addFoodImage: ImageView
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>
    private lateinit var imageUri: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityAddFoodBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.doneButton.setOnClickListener {
            addFood()
        }

        mBinding.addFoodImage.setOnClickListener{
            openMediaStore()
        }

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == Activity.RESULT_OK) {
                val intentData: Intent? = result.data
                intentData?.let { data ->
                    mBinding.addFoodImage.setImageURI(data.data)
                    imageUri = data.data.toString()
                }
            }
        }

        addFoodName = mBinding.addFoodName
        addFoodIngredients = mBinding.addFoodIngredients
        addFoodRecipe = mBinding.addRecipe
        addFoodImage = mBinding.addFoodImage
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            READ_EXTERNAL_STORAGE_REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadImage()
                } else {
                    val showRationale =
                        ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )

                    if (showRationale) {
                    } else {
                        goToSettings()
                    }
                }
                return
            }
        }
    }

    private fun goToSettings() {
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName")).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }.also { intent ->
            startActivity(intent)
        }
    }

    private fun addFood() {
        val resultIntent = Intent()

        if(addFoodName.text.isNullOrEmpty()
            || addFoodIngredients.text.isNullOrEmpty()
            || addFoodRecipe.text.isNullOrEmpty()
            || addFoodImage.drawable==null) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val name = addFoodName.text.toString()
            val ingredients = addFoodIngredients.text.toString()
            val recipe = addFoodRecipe.text.toString()
            resultIntent.putExtra(FOOD_NAME, name)
            resultIntent.putExtra(FOOD_INGREDIENTS, ingredients)
            resultIntent.putExtra(FOOD_RECIPE, recipe)
            resultIntent.putExtra(FOOD_IMAGE, imageUri.toString())
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }

    private fun loadImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        resultLauncher.launch(intent)
    }

    private fun openMediaStore() {
        if (haveStoragePermission()) {
            loadImage()

        } else {
            requestPermission()
        }
    }

    private fun haveStoragePermission() =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        if (!haveStoragePermission()) {
            val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            ActivityCompat.requestPermissions(this, permissions, READ_EXTERNAL_STORAGE_REQUEST)
        }
    }
}