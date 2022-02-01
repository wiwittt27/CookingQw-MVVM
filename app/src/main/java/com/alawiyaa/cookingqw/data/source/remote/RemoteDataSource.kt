package com.alawiyaa.cookingqw.data.source.remote

import android.util.Log
import com.alawiyaa.cookingqw.data.source.remote.network.ApiConfig
import com.alawiyaa.cookingqw.data.source.remote.network.ApiService
import com.alawiyaa.cookingqw.data.source.remote.response.CategoryResponseMeal
import com.alawiyaa.cookingqw.data.source.remote.response.MealsItems
import com.alawiyaa.cookingqw.data.source.remote.response.ResponseMeal
import com.alawiyaa.cookingqw.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }


    suspend fun getMeal(param:String,callback: LoadMeal){
        ApiConfig.getApiService().getMeal(param).await().meals.let { listMovie ->
            callback.onAllMealReceived(
                listMovie
            )

        }
    }


    suspend fun getCategoryMeal(callback: LoadCategoryCallback) {
        ApiConfig.getApiService().getCategoryMeal().await().meals.let { listCategory ->
            callback.onCategoryReceived(listCategory)
        }
    }





    interface LoadMeal {
        fun onAllMealReceived(mealResponse: List<MealsItems>)
    }

    interface LoadCategoryCallback {
        fun onCategoryReceived(mealResponse: List<CategoryResponseMeal.MealsItemCategory>)
    }


}