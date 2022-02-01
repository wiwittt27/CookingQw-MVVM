package com.alawiyaa.cookingqw.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alawiyaa.cookingqw.data.Category
import com.alawiyaa.cookingqw.data.Meal
import com.alawiyaa.cookingqw.data.source.remote.RemoteDataSource
import com.alawiyaa.cookingqw.data.source.remote.response.CategoryResponseMeal
import com.alawiyaa.cookingqw.data.source.remote.response.MealsItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MealRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MealDataSource {

    companion object {
        @Volatile
        private var instance: MealRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MealRepository =
            instance ?: synchronized(this) {
                instance ?: MealRepository(remoteData)
            }
    }



    override fun getListMeal(param:String): LiveData<List<Meal>> {
        val listMovieResult = MutableLiveData<List<Meal>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMeal(param,object : RemoteDataSource.LoadMeal{
                override fun onAllMealReceived(mealResponse: List<MealsItems>) {
                    val mealList = ArrayList<Meal>()
                    for (response in mealResponse){
                        val meal = Meal(
                            response.idMeal,
                            response.strCategory,
                            response.strMealThumb,
                            response.strMeal,
                            response.strSource,
                            response.strTags,
                            response.strArea,
                            response.strYoutube,
                            response.strInstructions,
                            response.strIngredient1,
                            response.strIngredient2,
                            response.strIngredient3,
                            response.strIngredient4,
                            response.strIngredient5,
                            response.strIngredient6,
                            response.strIngredient7,
                            response.strIngredient8,
                            response.strIngredient9,
                            response.strIngredient10,
                            response.strIngredient11,
                            response.strIngredient12,
                            response.strIngredient13,
                            response.strIngredient14,
                            response.strIngredient15,
                            response.strIngredient16,
                            response.strIngredient17,
                            response.strIngredient18,
                            response.strIngredient19,
                            response.strIngredient20,
                            response.strMeasure1,
                            response.strMeasure2,
                            response.strMeasure3,
                            response.strMeasure4,
                            response.strMeasure5,
                            response.strMeasure6,
                            response.strMeasure7,
                            response.strMeasure8,
                            response.strMeasure9,
                            response.strMeasure10,
                            response.strMeasure11,
                            response.strMeasure12,
                            response.strMeasure13,
                            response.strMeasure14,
                            response.strMeasure15,
                            response.strMeasure16,
                            response.strMeasure17,
                            response.strMeasure18,
                            response.strMeasure19,
                            response.strMeasure20

                        )
                        mealList.add(meal)
                    }
                    listMovieResult.postValue(mealList)
                }
            })
        }
        return listMovieResult
    }



    override fun getCategoryMeal(): LiveData<List<Category>> {
        val listCategoryResult = MutableLiveData<List<Category>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getCategoryMeal(object : RemoteDataSource.LoadCategoryCallback{
                override fun onCategoryReceived(mealResponse: List<CategoryResponseMeal.MealsItemCategory>) {
                    val categoryList = ArrayList<Category>()
                    for (response in mealResponse){
                        val categoryMeal = Category(
                            response.strCategory
                        )
                        categoryList.add(categoryMeal)

                    }
                    listCategoryResult.postValue(categoryList)


                }
            })
        }
        return listCategoryResult
    }



}