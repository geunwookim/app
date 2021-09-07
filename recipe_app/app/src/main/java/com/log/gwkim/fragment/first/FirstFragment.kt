package com.log.gwkim.fragment.first

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.log.gwkim.addFood.*
import com.log.gwkim.R
import com.log.gwkim.R.*
import com.log.gwkim.data.FoodData
import com.log.gwkim.data.foodList
import com.log.gwkim.databinding.FragmentFirstBinding
import com.log.gwkim.foodDetail.FoodDetailActivity
import dagger.hilt.android.AndroidEntryPoint

const val FOOD_ID = "food id"

@AndroidEntryPoint
class FirstFragment : Fragment() {
    private lateinit var mBinding : FragmentFirstBinding
    private lateinit var recyclerView : RecyclerView
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>
    private val foodViewModel : FoodViewModel by viewModels()
    private val foodAdapter = FoodAdapter { foodData ->  adapterOnClick(foodData) }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, layout.fragment_first, container, false)

        recyclerView = mBinding.root.findViewById(R.id.recycler_view)
        recyclerView.adapter = foodAdapter

        val fab: View = mBinding.root.findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == Activity.RESULT_OK) {
                val intentData: Intent? = result.data
                intentData?.let { data ->
                    val foodId = data.getLongExtra(FOOD_ID, 0)
                    val foodName = data.getStringExtra(FOOD_NAME)
                    val foodIngredients = data.getStringExtra(FOOD_INGREDIENTS)
                    val foodRecipe = data.getStringExtra(FOOD_RECIPE)
                    val foodImageByteArray : ByteArray? = data.getByteArrayExtra(FOOD_IMAGE)
                    foodViewModel.insertFood(foodId!!.toLong(), foodName, foodIngredients, foodRecipe, foodImageByteArray)
                }
            }
        }

        foodViewModel.initCheckIsDone().observe(viewLifecycleOwner) {
            if(it) {
                if(foodViewModel.getInitData().isEmpty()) {
                    Log.d("gwkim","first execution")
                    foodViewModel.initCheckInsert()

                    val initialFoodList = foodList()
                    for(i in initialFoodList.indices) {
                        foodViewModel.insertFood(initialFoodList[i].id, initialFoodList[i].name,
                            initialFoodList[i].ingredients,
                            initialFoodList[i].recipe,
                            null)
                    }
                } else {
                    Log.d("gwkim","not first execution")
                    foodViewModel.initDataInsert()
                }
            }
        }

        foodViewModel.initDataIsDone().observe(viewLifecycleOwner) {
            if(it) {
                foodViewModel.getFoodList().observe(viewLifecycleOwner, { foodDataList ->
                    foodDataList?.let {
                        foodAdapter.submitList(foodDataList as MutableList<FoodData>)
                    }
                })
            }
        }

        foodViewModel.initCheckIsFirst()

        return mBinding.root
    }

    private fun adapterOnClick(foodData: FoodData) {
        val intent = Intent(context, FoodDetailActivity()::class.java)
        intent.putExtra(FOOD_ID, foodData.id)
        resultLauncher.launch(intent)
    }

    private fun fabOnClick() {
        val intent = Intent(context, AddFoodActivity::class.java)
        intent.putExtra(FOOD_ID, foodViewModel.getFoodList().value?.last()?.id)
        resultLauncher.launch(intent)
    }
}