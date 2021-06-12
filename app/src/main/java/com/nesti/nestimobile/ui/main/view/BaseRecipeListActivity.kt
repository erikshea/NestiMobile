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

/**
 * base class for activities that show a list of recipes
 */
abstract class BaseRecipeListActivity<TViewModel: BaseRecipeListViewModel> : BaseActivity<TViewModel>() {
    protected lateinit var adapter: RecipeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_recipe_list)
        super.onCreate(savedInstanceState)
        setupObserver()
    }
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


    /**
     * sets up logic related to events signaled by observable objects returned by the viewmodel
     * (such as rendering a list when it's status changes to SUCCESS)
     */
    protected fun setupObserver() {
        viewModel.getRecipes().observe(this, Observer {
            // viewModel returns an observable Single containing a resource which can have 3
            // statuses depending on API request status
            when (it.status) {
                Status.SUCCESS -> {
                    // If Resource's status property becomes "SUCCESS", we know its data property
                    // contains the fetched list of entities
                    progress_bar.visibility = View.GONE // hide loading indicator
                    it.data?.let { recipes -> renderList(recipes) } // call renderList with fetched data
                    recycler_view.visibility = View.VISIBLE // show list
                }
                Status.LOADING -> {
                    // If Resource's status becomes "LOADING", its data property is still empty
                    progress_bar.visibility = View.VISIBLE // show loading indicator
                    recycler_view.visibility = View.GONE  // hide list
                }
                Status.ERROR -> {
                    // If Resource's status becomes "ERROR", API fetch failed
                    progress_bar.visibility = View.GONE // hide loading indicator
                    // show error message
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    /**
     * passes fetched data to this activity's RecyclerView adapter
     */
    protected fun renderList(recipes: List<Recipe>) {
        adapter.addData(recipes)
        adapter.notifyDataSetChanged()
    }
}