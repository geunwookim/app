package com.log.myapplication.fragment.first

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.log.myapplication.R
import com.log.myapplication.R.*
import com.log.myapplication.addFood.*
import com.log.myapplication.data.FoodData
import com.log.myapplication.databinding.FragmentFirstBinding
import com.log.myapplication.foodDetail.FoodDetailActivity
import dagger.hilt.android.AndroidEntryPoint

const val FOOD_ID = "food id"

@AndroidEntryPoint
class FirstFragment : Fragment() {
    private lateinit var mBinding : FragmentFirstBinding
    private lateinit var recyclerView : RecyclerView
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>
    private val foodViewModel : FoodViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, layout.fragment_first, container, false)

        val foodAdapter = FoodAdapter { foodData ->  adapterOnClick(foodData) }

        recyclerView = mBinding.root.findViewById(R.id.recycler_view)
        recyclerView.adapter = foodAdapter

        foodViewModel.getFoodList().observe(viewLifecycleOwner, {
            it?.let {
                foodAdapter.submitList(it as MutableList<FoodData>)
            }
        })

        val fab: View = mBinding.root.findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == Activity.RESULT_OK) {
                val intentData: Intent? = result.data
                intentData?.let { data ->
                    val foodName = data.getStringExtra(FOOD_NAME)
                    val foodIngredients = data.getStringExtra(FOOD_INGREDIENTS)
                    val foodRecipe = data.getStringExtra(FOOD_RECIPE)
                    val foodImageByteArray : ByteArray? = data.getByteArrayExtra(FOOD_IMAGE)
                    foodViewModel.insertFood(foodName, foodIngredients, foodRecipe, foodImageByteArray)
                }
            }
        }

        return mBinding.root
    }

    private fun adapterOnClick(foodData: FoodData) {
        val intent = Intent(context, FoodDetailActivity()::class.java)
        intent.putExtra(FOOD_ID, foodData.id)
        resultLauncher.launch(intent)
    }

    private fun fabOnClick() {
        val intent = Intent(context, AddFoodActivity::class.java)
        resultLauncher.launch(intent)
    }
}