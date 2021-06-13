package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.model.Ingredient
import com.nesti.nestimobile.ui.main.adapter.ShoppingListAdapter
import com.nesti.nestimobile.ui.main.view.base.BaseActivity
import com.nesti.nestimobile.ui.main.viewmodel.ShoppingListViewModel
import kotlinx.android.synthetic.main.activity_shopping_list.*

/**
 * activity for shopping list
 */
class ShoppingListActivity : BaseActivity() {
    lateinit var viewModel: ShoppingListViewModel

    private lateinit var adapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_shopping_list)
        super.onCreate(savedInstanceState)
        viewModel = ShoppingListViewModel(application)
        title = resources.getString(R.string.shopping_list_title)
        setupObserver(viewModel.getIngredients()) { ingredients -> adapter.setData(ingredients) }
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

    /**
     * passes fetched data to this activity's RecyclerView adapter
     */
    private fun renderList(ingredients: List<Ingredient>) {
        adapter.setData(ingredients)
    }

    /**
     * called when clear button is pressed
     */
    fun clearButtonClicked(view: View) {
        viewModel.deleteAll(); // clear ingredients table. will notify observer of change
    }
}