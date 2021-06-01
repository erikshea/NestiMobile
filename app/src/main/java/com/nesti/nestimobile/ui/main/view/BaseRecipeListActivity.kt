package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.ui.main.adapter.RecipeListAdapter
import com.nesti.nestimobile.ui.main.viewmodel.BaseRecipeListViewModel
import com.nesti.nestimobile.utils.Status
import kotlinx.android.synthetic.main.activity_recipe_list.*


abstract class BaseRecipeListActivity<TViewModel: BaseRecipeListViewModel> : BaseActivity<TViewModel>() {
    protected lateinit var adapter: RecipeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_recipe_list)
        super.onCreate(savedInstanceState)
        setupObserver()
    }
    override fun setupUI() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = RecipeListAdapter(arrayListOf())
        recycler_view.addItemDecoration(
                DividerItemDecoration(
                        recycler_view.context,
                        (recycler_view.layoutManager as LinearLayoutManager).orientation
                )
        )
        recycler_view.adapter = adapter
    }

    protected fun renderList(recipes: List<Recipe>) {
        adapter.addData(recipes)
        adapter.notifyDataSetChanged()
    }

    protected fun setupObserver() {
        viewModel.getRecipes().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    it.data?.let { recipes -> renderList(recipes) }
                    recycler_view.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}