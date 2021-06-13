package com.nesti.nestimobile.ui.main.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.nesti.nestimobile.lib.NestiMobileApplication
import com.nesti.nestimobile.ui.base.NestiViewModelFactory
import com.nesti.nestimobile.ui.main.view.base.BaseRecipeListActivity
import com.nesti.nestimobile.ui.main.viewmodel.CategoryViewModel

/**
 * shows a list of recipes for the clicked category
 */
class CategoryActivity : BaseRecipeListActivity<CategoryViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = intent.getStringExtra("com.nesti.nestimobile.category.title");
    }

    /**
     * Initializes viewmodel from class type parameter
     */
    override fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            NestiViewModelFactory(applicationContext as NestiMobileApplication)
        ).get(CategoryViewModel::class.java)

        // viewmodel needs tag corresponding to current activity
        viewModel.tag = intent.getParcelableExtra("com.nesti.nestimobile.category.tag")!!;
    }
}