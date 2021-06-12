package com.nesti.nestimobile.ui.main.view

import IngredientDao
import android.R.attr.data
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Ingredient
import com.nesti.nestimobile.ui.main.adapter.ShoppingListAdapter
import com.nesti.nestimobile.ui.main.viewmodel.ShoppingListViewModel
import com.nesti.nestimobile.utils.Resource
import com.nesti.nestimobile.utils.Status
import kotlinx.android.synthetic.main.activity_shopping_list.*

/**
 * activity for shopping list
 */
class ShoppingListActivity : BaseActivity<ShoppingListViewModel>() {
    private lateinit var adapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_shopping_list)
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.shopping_list_title)
        setupObserver()
    }

    override fun setupUi() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = ShoppingListAdapter(arrayListOf())
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
            // viewModel returns an observable Single containing a resource which can have 3
            // statuses depending on API request status
            when (it.status) {
                Status.SUCCESS -> {
                    // If Resource's status property becomes "SUCCESS", we know its data property
                    // contains the fetched list of entities
                    progress_bar.visibility = View.GONE // hide loading indicator
                    it.data?.let { list -> renderList(list) } // call renderList with fetched data
                    recycler_view.visibility = View.VISIBLE // show list
                }
                Status.LOADING -> {
                    // If Resource's status becomes "LOADING", its data property is still empty
                    progress_bar.visibility = View.VISIBLE // show loading indicator
                    recycler_view.visibility = View.GONE // hide list
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
    private fun renderList(ingredients: List<Ingredient>) {
        adapter.setData(ingredients)
    }

    override fun setupViewModel() {
        viewModel =ShoppingListViewModel(application)
    }

    /**
     * called when clear button is pressed
     */
    fun clearButtonClicked(view: View) {
        viewModel.deleteAll(); // clear ingredients table. will notify observer of change
    }
}