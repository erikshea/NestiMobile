package com.nesti.nestimobile.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.IngredientRecipe
import com.nesti.nestimobile.ui.main.adapter.IngredientListAdapter
import com.nesti.nestimobile.utils.Status
import kotlinx.android.synthetic.main.fragment_recipe_ingredients.*


class RecipeIngredientsFragment() : BaseRecipeFragment()  {
    override val layout = R.layout.fragment_recipe_ingredients;
    private lateinit var adapter: IngredientListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler_view.layoutManager = LinearLayoutManager(activity)
        adapter = IngredientListAdapter(arrayListOf())
        recycler_view.addItemDecoration(
                DividerItemDecoration(
                        recycler_view.context,
                        (recycler_view.layoutManager as LinearLayoutManager).orientation
                )
        )
        recycler_view.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getIngredients().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    it.data?.let { items -> renderList(items) }
                    recycler_view.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progress_bar.visibility = View.GONE
                }
            }
        })
    }

    private fun renderList(items: List<IngredientRecipe>) {
        adapter.addData(items)
        adapter.notifyDataSetChanged()
    }


    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

}