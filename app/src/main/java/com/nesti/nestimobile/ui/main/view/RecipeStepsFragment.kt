package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nesti.nestimobile.R
import com.nesti.nestimobile.ui.main.adapter.ParagraphListAdapter
import com.nesti.nestimobile.ui.main.view.base.BaseRecipeFragment
import kotlinx.android.synthetic.main.fragment_recipe_steps.*

/**
 * steps tab content for a recipe
 */
class RecipeStepsFragment() : BaseRecipeFragment()  {
    override val layout = R.layout.fragment_recipe_steps;
    private lateinit var adapter: ParagraphListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver(viewModel.getParagraphs()) { paragraphs -> adapter.addData(paragraphs) }
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
}