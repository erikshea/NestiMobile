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
import kotlinx.android.synthetic.main.activity_main.*


abstract class BaseRecipeListActivity<TViewModel: BaseRecipeListViewModel> : BaseActivity<TViewModel>() {
    protected lateinit var adapter: RecipeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_recipe_list)
        super.onCreate(savedInstanceState)
        setupObserver()
    }
    override fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecipeListAdapter(arrayListOf())
        recyclerView.addItemDecoration(
                DividerItemDecoration(
                        recyclerView.context,
                        (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
        )
        recyclerView.adapter = adapter
    }

    protected fun renderList(recipes: List<Recipe>) {
        adapter.addData(recipes)
        adapter.notifyDataSetChanged()
    }

    protected fun setupObserver() {
        viewModel.getRecipes().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { recipes -> renderList(recipes) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}