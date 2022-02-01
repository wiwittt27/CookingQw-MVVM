package com.alawiyaa.cookingqw.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class 	CategoryResponseMeal(

	@field:SerializedName("meals")
	val meals: List<MealsItemCategory>
) {
	data class MealsItemCategory(

		@field:SerializedName("strCategory")
		val strCategory: String,
		@field:SerializedName("strMeal")
		val strMeal: String
	)

}