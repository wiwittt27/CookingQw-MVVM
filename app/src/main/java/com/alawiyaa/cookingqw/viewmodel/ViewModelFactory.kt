package com.alawiyaa.cookingqw.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alawiyaa.cookingqw.data.source.MealRepository
import com.alawiyaa.cookingqw.di.Injection
import com.alawiyaa.cookingqw.ui.listMeal.MealViewModel

class ViewModelFactory private constructor(private val mMealRepository: MealRepository) : ViewModelProvider.NewInstanceFactory(){
    companion object{

        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance() : ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }

    }
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return  when{
            modelClass.isAssignableFrom(MealViewModel::class.java)->{
                 MealViewModel(mMealRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }


    }
}