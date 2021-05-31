package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.api.ApiService
import com.nesti.nestimobile.data.model.IngredientRecipe
import com.nesti.nestimobile.data.model.Paragraph
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.ui.base.ViewModelFactory
import com.nesti.nestimobile.ui.main.adapter.IngredientListAdapter
import com.nesti.nestimobile.ui.main.adapter.ParagraphListAdapter
import com.nesti.nestimobile.ui.main.viewmodel.RecipeViewModel
import com.nesti.nestimobile.utils.ApiHelper
import com.nesti.nestimobile.utils.Status
import kotlinx.android.synthetic.main.fragment_recipe_steps.*


class RecipeStepsFragment() : BaseRecipeFragment()  {
    override val layout = R.layout.fragment_recipe_steps;
    private lateinit var adapter: ParagraphListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler_view.layoutManager = LinearLayoutManager(activity)
        adapter = ParagraphListAdapter(arrayListOf())
        recycler_view.addItemDecoration(
                DividerItemDecoration(
                        recycler_view.context,
                        (recycler_view.layoutManager as LinearLayoutManager).orientation
                )
        )
        recycler_view.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getParagraphs().observe(this, Observer {
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

    private fun renderList(items: List<Paragraph>) {
        adapter.addData(items)
        adapter.notifyDataSetChanged()
    }
}