package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nesti.nestimobile.R
import com.nesti.nestimobile.ui.main.adapter.IngredientListAdapter
import com.nesti.nestimobile.ui.main.view.base.BaseRecipeFragment
import kotlinx.android.synthetic.main.fragment_recipe_ingredients.*

/**
 * ingredients tab content for a recipe
 */
class RecipeIngredientsFragment() : BaseRecipeFragment()  {
    override val layout = R.layout.fragment_recipe_ingredients;
    private lateinit var adapter: IngredientListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver(viewModel.getIngredients()) { ingredients -> adapter.addData(ingredients) }
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

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}