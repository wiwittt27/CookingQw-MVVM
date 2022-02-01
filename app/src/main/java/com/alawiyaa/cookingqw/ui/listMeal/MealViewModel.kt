package com.alawiyaa.cookingqw.ui.listMeal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alawiyaa.cookingqw.data.Category
import com.alawiyaa.cookingqw.data.Meal
import com.alawiyaa.cookingqw.data.source.MealRepository

class MealViewModel(private val mealRepository: MealRepository) : ViewModel() {

//    fun getSearchMeal() : LiveData<List<MealListEntity>> = mealRepository.getSearchMeal()

    fun getMeal(param:String) : LiveData<List<Meal>> = mealRepository.getListMeal(param)

    fun getCategoryMeal() : LiveData<List<Category>> = mealRepository.getCategoryMeal()


}