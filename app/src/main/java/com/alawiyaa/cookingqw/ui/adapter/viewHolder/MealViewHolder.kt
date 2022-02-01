package com.alawiyaa.cookingqw.ui.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.alawiyaa.cookingqw.data.Meal
import com.alawiyaa.cookingqw.databinding.ItemMealBinding
import com.bumptech.glide.Glide


class MealViewHolder(private val binding: ItemMealBinding,
                    private val listener: Listener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(meal : Meal){
        with(binding){
           tvTitle.text = meal.strMeal
            tvCategory.text = meal.strCategory
            Glide.with(itemView.context)
                .load(meal.strMealThumb)
                .into(imgMeal)
        itemView.setOnClickListener {
            listener.onClick(meal)
        }
        }

    }
    interface Listener{
        fun onClick(meal :Meal)
    }
}
