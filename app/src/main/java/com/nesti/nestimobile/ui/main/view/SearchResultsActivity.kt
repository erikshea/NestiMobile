package com.nesti.nestimobile.ui.main.view

import android.app.SearchManager
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import com.nesti.nestimobile.lib.NestiMobileApplication
import com.nesti.nestimobile.ui.base.NestiViewModelFactory
import com.nesti.nestimobile.ui.main.view.base.BaseRecipeListActivity
import com.nesti.nestimobile.ui.main.viewmodel.SearchResultsViewModel

/**
 * activity shown when a search is launched
 */
class SearchResultsActivity : BaseRecipeListActivity<SearchResultsViewModel>() {
    override fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            NestiViewModelFactory(applicationContext as NestiMobileApplication)
        ).get(SearchResultsViewModel::class.java)


        // if search term entered and validated, send query string to viewmodel which will update
        // observable recipe list
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                viewModel.searchTerm = query
            }
        }
    }

}