package com.alawiyaa.cookingqw.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alawiyaa.cookingqw.R
import com.alawiyaa.cookingqw.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    var _binding : ActivityHomeBinding? =null
    val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

       setSupportActionBar(binding?.toolBar)

    }
}