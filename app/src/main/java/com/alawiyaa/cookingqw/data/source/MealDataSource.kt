package com.alawiyaa.cookingqw.data.source

import androidx.lifecycle.LiveData
import com.alawiyaa.cookingqw.data.Category
import com.alawiyaa.cookingqw.data.Meal

interface MealDataSource {
     fun getListMeal(param:String): LiveData<List<Meal>>
     fun getCategoryMeal() : LiveData<List<Category>>
}