package com.alawiyaa.cookingqw.utils

import androidx.recyclerview.widget.DiffUtil
import com.alawiyaa.cookingqw.data.Meal

class MealDiffCallback(
    private val mOldMealList: List<Meal>,
    private val mNewsMealList: List<Meal>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldMealList.size
    }

    override fun getNewListSize(): Int {
        return mNewsMealList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldMealList[oldItemPosition].idMeal == mNewsMealList[newItemPosition].idMeal
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMeals = mOldMealList[oldItemPosition]
        val newMeals = mNewsMealList[newItemPosition]
        return when {
            mOldMealList[oldItemPosition].idMeal != mNewsMealList[newItemPosition].idMeal -> {
                false
            }
            mOldMealList[oldItemPosition].strMeal != mNewsMealList[newItemPosition].strMeal -> {
                false
            }
            mOldMealList[oldItemPosition].strCategory != mNewsMealList[newItemPosition].strCategory ->{
                false
            }
            else ->true
        }
    }
}