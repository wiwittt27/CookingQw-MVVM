package com.alawiyaa.cookingqw.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alawiyaa.cookingqw.data.Meal
import com.alawiyaa.cookingqw.databinding.ItemMealBinding
import com.alawiyaa.cookingqw.ui.adapter.viewHolder.MealViewHolder
import com.alawiyaa.cookingqw.utils.MealDiffCallback

class AdapterMeal(val listener : MealViewHolder.Listener) : RecyclerView.Adapter<MealViewHolder>() {


    private var listMewl = ArrayList<Meal>()
    fun setListNotes(listNotes: List<Meal>) {
        val diffCallback = MealDiffCallback(this.listMewl, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listMewl = listNotes as ArrayList<Meal>
        diffResult.dispatchUpdatesTo(this)

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder =
        MealViewHolder(
            ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener
        )

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(listMewl[position])
    }

    override fun getItemCount(): Int = listMewl.count()


}

