package com.alawiyaa.cookingqw.di

import android.content.Context
import com.alawiyaa.cookingqw.data.source.MealRepository
import com.alawiyaa.cookingqw.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository() : MealRepository{
        val remoteDataSource = RemoteDataSource.getInstance()
        return MealRepository.getInstance(remoteDataSource)
    }

}