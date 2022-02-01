package com.alawiyaa.cookingqw.data.source.remote.network

import com.alawiyaa.cookingqw.BuildConfig
import com.alawiyaa.cookingqw.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.php")
    fun getMeal(
        @Query("s")
        s: String? = null
    ): Call<ResponseMeal<MealsItems>>

    @GET("lookup.php")
    fun getDetailMeal(@Query("i") s: Int)
            : Call<MealsItems>

    @GET("list.php?c=list")
    fun getCategoryMeal(): Call<CategoryResponseMeal>

}