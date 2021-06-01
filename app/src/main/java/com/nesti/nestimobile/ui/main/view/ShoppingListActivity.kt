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
import com.nesti.nestimobile.utils.Status
import kotlinx.android.synthetic.main.activity_shopping_list.*


class ShoppingListActivity : BaseActivity<ShoppingListViewModel>() {
    private lateinit var adapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_shopping_list)
        super.onCreate(savedInstanceState)
        setTitle("Liste de courses")
        setupObserver()

    }

    override fun setupUI() {
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
            when (it.status) {
                Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    it.data?.let { list -> renderList(list) }
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

    private fun renderList(ingredients: List<Ingredient>) {
        adapter.addData(ingredients)
        adapter.notifyDataSetChanged()
    }

    override fun setupViewModel() {
        viewModel =ShoppingListViewModel(application)
    }

    fun trashButtonClicked(view: View) {
        val ingredientDao = IngredientDao(application)
        ingredientDao.delete(Ingredient(idIngredient = view.tag as Int))

        adapter.ingredients.clear();
        Handler(Looper.getMainLooper()).post(Runnable { adapter.notifyDataSetChanged() })
        viewModel.fetchIngredients()

        recycler_view.adapter = null
        recycler_view.adapter = adapter
        //Handler(Looper.getMainLooper()).post(Runnable { adapter.notifyDataSetChanged() })

    }

    fun clearButtonClicked(view: View) {
        println("d")
    }
}