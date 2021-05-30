package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nesti.nestimobile.R
import com.nesti.nestimobile.data.api.ApiService
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.ui.base.ViewModelFactory
import com.nesti.nestimobile.ui.main.adapter.RecipeListAdapter
import com.nesti.nestimobile.ui.main.viewmodel.CategoryViewModel
import com.nesti.nestimobile.ui.main.viewmodel.SearchResultsViewModel
import com.nesti.nestimobile.utils.ApiHelper
import kotlinx.android.synthetic.main.activity_main.*


class SearchResultsActivity : BaseRecipeListActivity<SearchResultsViewModel>() {

    override fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiService()))
        ).get(SearchResultsViewModel::class.java)

        viewModel.searchTerm = intent.getStringExtra("com.nesti.nestimobile.searchTerm").toString()
    }
}