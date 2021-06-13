package com.nesti.nestimobile.ui.main.view.base

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nesti.nestimobile.R
import com.nesti.nestimobile.ui.main.adapter.RecipeListAdapter
import com.nesti.nestimobile.ui.main.viewmodel.base.BaseRecipeListViewModel
import kotlinx.android.synthetic.main.activity_recipe_list.*

/**
 * base class for activities that show a list of recipes
 */
abstract class BaseRecipeListActivity<TViewModel: BaseRecipeListViewModel> : BaseActivity() {
    protected lateinit var adapter: RecipeListAdapter
    lateinit var viewModel: TViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_recipe_list)
        super.onCreate(savedInstanceState)
        setupViewModel()
        setupObserver(viewModel.getRecipes()) { recipes -> adapter.addData(recipes) }
    }

    abstract fun setupViewModel()

    override fun setupUi() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = RecipeListAdapter(arrayListOf())

        // add separator between list items
        recycler_view.addItemDecoration(
                DividerItemDecoration(
                        recycler_view.context,
                        (recycler_view.layoutManager as LinearLayoutManager).orientation
                )
        )

        recycler_view.adapter = adapter
    }
}