package com.log.myapplication.fragment.first

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.log.myapplication.R
import com.log.myapplication.data.FoodData

class FoodAdapter(private val onClick: (FoodData) -> Unit)
    : ListAdapter<FoodData, FoodAdapter.FoodViewHolder>(FoodDiffCallback) {

    class FoodViewHolder(view: View, val onClick: (FoodData) -> Unit)
    : RecyclerView.ViewHolder(view) {
        private val foodTextView: TextView = itemView.findViewById(R.id.foodNameItem)
        private val foodImageView: ImageView = itemView.findViewById(R.id.foodImage)
        private var currentFood: FoodData? = null

        init {
            view.setOnClickListener {
                currentFood?.let {
                    onClick(it)
                }
            }
        }

        fun bind(foodData: FoodData) {
            currentFood = foodData

            foodTextView.text = foodData.name
            if(foodData.image != null) {
                foodImageView.setImageResource(foodData.image)
            } else {
                foodImageView.setImageResource(R.drawable.korea)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.food_list, viewGroup, false)
        return FoodViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: FoodViewHolder, position: Int) {
        val foodList = getItem(position)
        viewHolder.bind(foodList)
    }
}

object FoodDiffCallback : DiffUtil.ItemCallback<FoodData>() {
    override fun areItemsTheSame(oldItem: FoodData, newItem: FoodData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FoodData, newItem: FoodData): Boolean {
        return oldItem.id == newItem.id
    }
}