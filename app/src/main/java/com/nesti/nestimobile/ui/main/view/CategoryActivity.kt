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
import com.nesti.nestimobile.utils.ApiHelper


class CategoryActivity : BaseRecipeListActivity<CategoryViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = intent.getStringExtra("com.nesti.nestimobile.title");
    }


    override fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiService()))
        ).get(CategoryViewModel::class.java)

        viewModel.tag = intent.getParcelableExtra("com.nesti.nestimobile.tag")!!;
    }
}