package com.alawiyaa.cookingqw.ui.detailMeal

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.alawiyaa.cookingqw.R
import com.alawiyaa.cookingqw.data.Meal
import com.alawiyaa.cookingqw.data.source.remote.response.MealsItems
import com.alawiyaa.cookingqw.databinding.FragmentDetailMealBinding
import com.alawiyaa.cookingqw.utils.Ingredient
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import java.lang.reflect.Array.get
import kotlin.math.ceil


class DetailMealFragment : Fragment() {
    var _binding: FragmentDetailMealBinding? = null
    val binding get() = _binding
    private val Int.toDp get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
    var convert = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailMealBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val meal = DetailMealFragmentArgs.fromBundle(arguments as Bundle).detailMeal
        showDataMeal(meal)

        initView()



    }

    private fun initView() {
        val fab = binding?.floatingButton
        val lp = fab?.layoutParams as CoordinatorLayout.LayoutParams
        setHasOptionsMenu(true)

        (binding?.layoutDetail?.labelInstruction?.layoutParams as ConstraintLayout.LayoutParams).apply {
            topMargin= (-convert).toDp

        }


        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        binding?.collapsingToolbar?.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
        binding?.collapsingToolbar?.setExpandedTitleColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )

        binding?.collapsingToolbar?.setExpandedTitleColor(Color.TRANSPARENT)
        binding?.appBar?.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                //Initialize the size of the scroll
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                //Check if the view is collapsed
                if (scrollRange + verticalOffset == 0) {
                    binding?.toolBarCustom?.visibility = View.VISIBLE
                    fab.layoutParams = lp

                } else {
                    binding?.toolBarCustom?.visibility = View.INVISIBLE
                    fab.layoutParams = lp



                }
            }
        })
    }

    private fun showDataMeal(meal: Meal) {

        val int = Ingredient.strIngredient(meal)
        val nonum = int.filter { it != "" }

        convert = (20 - nonum.size) * 15

        binding?.imgDetailNews?.let {
            activity?.let { it1 -> Glide.with(it1) }?.load(meal.strMealThumb)?.into(it)
        }
        binding?.collapsingToolbar?.title = meal.strMeal


        val number = Ingredient.strIngredient(meal)
        val noNumber = number.filterNotNull()
        //Log.d("TINGGIS","${noNumber.count()}")

        binding?.layoutDetail?.tvTitleMeal?.text = meal.strMeal
        binding?.layoutDetail?.tvMealType?.text = getString(R.string.str_area, meal.strArea)
        binding?.layoutDetail?.tvMealCategory?.text = meal.strCategory
        binding?.layoutDetail?.tvMealTags?.text = meal.strTags ?: "-"
        Ingredient.strIngredient(meal).forEach {ingredient ->

            binding?.layoutDetail?.tvMealIngredient?.append(ingredient + "\n")
        }
        Ingredient.strMeasure(meal).forEach {measure->

            binding?.layoutDetail?.tvMealMeasure?.append(measure + "\n")
            Log.d("TINGGI","${measure?.length}")

        }




        binding?.layoutDetail?.tvMealInstruction?.text = meal.strInstructions
        binding?.floatingButton?.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(meal.strYoutube)))
        }
    }
}