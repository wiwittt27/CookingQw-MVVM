package com.alawiyaa.cookingqw.ui.listMeal

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alawiyaa.cookingqw.R
import com.alawiyaa.cookingqw.data.Meal
import com.alawiyaa.cookingqw.databinding.FragmentMealBinding
import com.alawiyaa.cookingqw.ui.adapter.AdapterMeal
import com.alawiyaa.cookingqw.ui.adapter.viewHolder.MealViewHolder
import com.alawiyaa.cookingqw.utils.BaseFragment
import com.alawiyaa.cookingqw.viewmodel.ViewModelFactory


class MealFragment : BaseFragment(), MealViewHolder.Listener {
    private var _binding: FragmentMealBinding? = null
    private val binding get() = _binding
    private lateinit var adapterMeal: AdapterMeal
    private var mealParam = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealBinding.inflate(inflater, container, false)
        return binding?.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            initView()
            initObserve()
        }


    }

    private fun initView() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.show()
//        binding?.swipeRefresh?.setOnRefreshListener(this)
    }

    private fun initObserve() {
         val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[MealViewModel::class.java]
        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.getMeal(mealParam).observe(viewLifecycleOwner, { listMeal ->
            binding?.progressBar?.visibility = View.GONE
            showListMeal(listMeal)
          if (adapterMeal.itemCount != 0){
              binding?.root?.let { showInformationMessage("ada", it) }

          }else{
              binding?.root?.let { showInformationMessage("Isi kolom pencarian!", it) }

          }

        })
    }

    private fun showListMeal(listMeal: List<Meal>) {
         adapterMeal = AdapterMeal(this)

        adapterMeal.setListNotes(listMeal)
        with(binding?.rvMeal) {
            this?.layoutManager = GridLayoutManager(context, 2)
            this?.adapter = adapterMeal
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[MealViewModel::class.java]
        inflater.inflate(R.menu.meal_menu, menu)
        val search = menu.findItem(R.id.item_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search Meal..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.getMeal(query).observe(viewLifecycleOwner,{
                        showListMeal(it)
                    })
                }else{
                    binding?.root?.let { showInformationMessage("Isi kolom pencarian!", it) }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.getMeal(newText).observe(viewLifecycleOwner,{
                        showListMeal(it)
                    })
                }else {
                    binding?.root?.let { showInformationMessage("Isi kolom pencarian!", it) }

                }
                return false
            }
        })

        search.icon.setVisible(false, false)

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[MealViewModel::class.java]
        when (item.itemId) {
            R.id.item_filter -> {

                val catAdapter = ArrayAdapter<String>(
                    requireContext(),
                    android.R.layout.simple_expandable_list_item_1
                )
                viewModel.getCategoryMeal().observe(viewLifecycleOwner, { category ->
                    category.forEach { item ->
                        catAdapter.addAll(item.strCategory)
                    }
                })
                AlertDialog.Builder(requireContext())
                    .setAdapter(catAdapter) { _, position ->
                        val category = catAdapter.getItem(position)
                        if (category != null) {
                            binding?.progressBar?.visibility = View.VISIBLE
                            viewModel.getMeal(category).observe(viewLifecycleOwner,{
                                binding?.progressBar?.visibility = View.GONE

                                showListMeal(it)
                            })
                            binding?.root?.let { showInformationMessage("Ini $category", it) }
                        }
                    }.show()

            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onClick(meal: Meal) {
    val detail = MealFragmentDirections.actionMealFragmentToDetailMealFragment(meal)
        view?.findNavController()?.navigate(detail)
    }




}